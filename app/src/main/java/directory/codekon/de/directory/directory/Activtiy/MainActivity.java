package directory.codekon.de.directory.directory.Activtiy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import java.util.ArrayList;
import directory.codekon.de.directory.R;
import directory.codekon.de.directory.directory.Controller.CustomExtandableListViewAdapter;
import directory.codekon.de.directory.directory.Database.DirectoryDatabase;

public class MainActivity extends Activity {

    private ArrayList<String> mGroupItems = new ArrayList<String>();
    private ArrayList<Object> mChildItems = new ArrayList<Object>();

    private DirectoryDatabase mDirectoryDataBase;
    private LayoutInflater mInflater;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDirectoryDataBase = new DirectoryDatabase(this);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);

        //TODO display city images
        mImageView = (ImageView) findViewById(R.id.imageView);

        getGroupData();
        getChildData();

        CustomExtandableListViewAdapter adapter = new CustomExtandableListViewAdapter(mGroupItems, mChildItems, this);

        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        adapter.setInflater(mInflater, this);

        listView.setAdapter(adapter);

    }


    public void getGroupData() {
        mGroupItems = mDirectoryDataBase.getCountries();
    }



    public void getChildData() {

        ArrayList<String> children = mDirectoryDataBase.getCities();

        //Türkiye
        ArrayList<String> child = new ArrayList<String>();

        child.add(children.get(0));
        child.add(children.get(1));
        child.add(children.get(2));

        mChildItems.add(child);


        // Almanya
        child = new ArrayList<String>();

        child.add(children.get(3));
        child.add(children.get(4));
        child.add(children.get(5));

        mChildItems.add(child);

    }

}
