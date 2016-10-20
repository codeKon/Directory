package directory.codekon.de.directory.directory.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DirectoryDatabase extends SQLiteOpenHelper {

    private static final String DB = "directoryDataBase";
    private static final int VERSION = 1;

    public DirectoryDatabase(Context context) {
        super(context, DB, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableCountry = "CREATE TABLE country (id INTEGER PRIMARY KEY AUTOINCREMENT," + "countryName TEXT COLLATE NOCASE)";
        String createTableCities= "CREATE TABLE city (id INTEGER PRIMARY KEY AUTOINCREMENT," + "cityName TEXT COLLATE NOCASE)";

        sqLiteDatabase.execSQL(createTableCountry);
        sqLiteDatabase.execSQL(createTableCities);

        insertCounryData(sqLiteDatabase);
        insertCityData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        sqLiteDatabase.execSQL("DROP TABLE if EXISTS country");
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS city");

        onCreate(sqLiteDatabase);
    }

    private void insertCounryData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO country VALUES(1,'Türkiye');");
        db.execSQL("INSERT INTO country VALUES(2,'Almanya');");
    }

    private void insertCityData(SQLiteDatabase db){
        db.execSQL("INSERT INTO city VALUES(1,'Ankara');");
        db.execSQL("INSERT INTO city VALUES(2,'Istanbul');");
        db.execSQL("INSERT INTO city VALUES(3,'Konya');");
        db.execSQL("INSERT INTO city VALUES(4,'Berlin');");
        db.execSQL("INSERT INTO city VALUES(5,'Bremen');");
        db.execSQL("INSERT INTO city VALUES(6,'Köln');");
    }

    public ArrayList<String> getCountries() {

        ArrayList<String> countries = new ArrayList<>();
        String selectQuery = "SELECT  * FROM country";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String country = cursor.getString(1);
                countries.add(country);
            } while (cursor.moveToNext());
        }

        return countries;
    }


    public ArrayList<String> getCities() {

        ArrayList<String> cities = new ArrayList<>();
        String selectQuery = "SELECT  * FROM city";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String city = cursor.getString(1);
                cities.add(city);
            } while (cursor.moveToNext());
        }

        return cities;
    }


}
