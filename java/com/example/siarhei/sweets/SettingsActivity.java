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


public class SettingsActivity extends Activity {

    private static final String LOG_TAG = "logs:Settings ->";
    private List<Sweet> sweets;
    protected BDSweets dbSweets;
    private ListView list;
    protected MyListAdapter adapter;
    protected Context context;
    private int view;
    private int listId;
    private String queryParametrs[];
    private String tableColums[];
    private String whereArgs[];
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
        sweets = new ArrayList<Sweet>();
        addSwets();

        adapter = new MyListAdapter(context,sweets,list);
        adapter.populateListView();
        registerClickCallback();
    }

    public void setQuery(String[] queryParametrs, String[] tableColums, String[] whereArgs){
        this.queryParametrs = queryParametrs;
        this.tableColums = tableColums;
        this.whereArgs = whereArgs;
    }

    protected List<Sweet> getSweets() { return sweets; }
    private void addSwets(){

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
        //dbSweets.close();
    }



    protected void registerClickCallback() {
        ListView list = (ListView) findViewById(listId);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                Sweet clickedSweet = sweets.get(position);
                Intent intent = new Intent(context, ShowActivity.class);
                intent.putExtra("clickedSweet",clickedSweet);
                startActivity(intent);
            }
        });
    }

   protected void countTotal(){}


}

