package directory.codekon.de.directory.directory.Controller;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import directory.codekon.de.directory.R;
import directory.codekon.de.directory.directory.Database.DirectoryDatabase;

public class CustomExtandableListViewAdapter extends BaseExpandableListAdapter {

    private ArrayList<Object> mChild;
    private ArrayList<String> mGroup;
    private ArrayList<String> mChildItem;
    private LayoutInflater mLayoutInflater;
    private Activity mContext;
    private DirectoryDatabase mDirectoryDataBase;


    public CustomExtandableListViewAdapter(ArrayList<String> groups, ArrayList<Object> childern, Activity context) {
        this.mChild = childern;
        this.mGroup = groups;

        mDirectoryDataBase = new DirectoryDatabase(context);

    }

    public void setInflater(LayoutInflater inflater, Activity context) {
        this.mLayoutInflater = inflater;
        this.mContext = context;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_group_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.header);
        textView.setText(mGroup.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        mChildItem = (ArrayList<String>) mChild.get(groupPosition);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_child_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.list_child);
        textView.setText(mChildItem.get(childPosition));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> cityList = mDirectoryDataBase.getCities();

                        for(int i = 0 ; i < cityList.size();i++){

                            if (mChildItem.get(childPosition).equals(cityList.get(i))) {

                                Toast toast = Toast.makeText(mContext, cityList.get(i),
                                        Toast.LENGTH_SHORT);

                                toast.show();

                            }
                }

            }
        });

        return convertView;
    }


    @Override
    public int getGroupCount() {
        return mGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) mChild.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
