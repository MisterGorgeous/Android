package com.example.siarhei.sweets;
import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import com.example.siarhei.sweets.bd.BDSweets;

        import java.util.ArrayList;
        import java.util.List;


public abstract class SettingsActivity extends Activity {

    private static final String LOG_TAG = "logs:Settings ->";

    protected BDSweets dbSweets;
    protected ListView list;
    protected MyListAdapter adapter;
    protected Context context;
    private int view;
    protected int listId;
    protected String queryParametrs[];
    protected String tableColums[];
    protected String whereArgs[];
    protected SQLiteDatabase db;


    public SettingsActivity( int view, int listId) {
        this.view = view;
        this.listId = listId;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        list = (ListView) findViewById(listId);
    }





    //protected List<Sweet> getSweets() { return sweets; }

    protected void addSwets(List<Sweet> sweets){

        dbSweets = new BDSweets(this);
        db = dbSweets.getWritableDatabase();


        Cursor c =  db.query(queryParametrs[0],tableColums,queryParametrs[1],
                whereArgs,queryParametrs[2],queryParametrs[3],queryParametrs[4]);

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameIndex = c.getColumnIndex("name");
            int iconIndex = c.getColumnIndex("icon");
            int infoIndex = c.getColumnIndex("info");
            int priceIndex = c.getColumnIndex("price");
            int typeOfSweets = c.getColumnIndex("typesofsw");
            int amountOfSweets = c.getColumnIndex("amount");

            do {
                sweets.add(new Sweet(c.getString(nameIndex),c.getFloat(priceIndex),c.getInt(iconIndex),
                        c.getInt(infoIndex),c.getInt(idColIndex),c.getInt(typeOfSweets),c.getInt(amountOfSweets)));
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();

        countTotal();
      //  dbSweets.close();
       // db.close();
    }

    abstract void setQuery(typesOfSweets clickedSweet);
    abstract void registerClickCallback();
    abstract void countTotal();


}