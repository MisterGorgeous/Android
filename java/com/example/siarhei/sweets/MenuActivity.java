package com.example.siarhei.sweets;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.siarhei.sweets.bd.BDSweets;
import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends Activity{
    private static final String LOG_TAG = "logs:MenuActivity";
    private List<Sweet> sweets = new ArrayList<Sweet>();
    private BDSweets dbSweets;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        list = (ListView) findViewById(R.id.listView);

        addSwets();

        MyListAdapter adapter = new MyListAdapter(MenuActivity.this,sweets,list);
        adapter.populateListView();
        registerClickCallback();
    }

    private void addSwets(){

        dbSweets = new BDSweets(this);
        SQLiteDatabase db = dbSweets.getWritableDatabase();


        Cursor c =  db.query("assortiment", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameIndex = c.getColumnIndex("name");
            int iconIndex = c.getColumnIndex("icon");
            int infoIndex = c.getColumnIndex("info");
            int priceIndex = c.getColumnIndex("price");
            int typeOfSweets = c.getColumnIndex("typesofsw");

            do {
                sweets.add(new Sweet(c.getString(nameIndex),c.getFloat(priceIndex),c.getInt(iconIndex),c.getInt(infoIndex),c.getInt(idColIndex),c.getInt(typeOfSweets)));
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();

        dbSweets.close();
    }



    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                Sweet clickedSweet = sweets.get(position);
                Intent intent = new Intent(MenuActivity.this, ShowActivity.class);
                intent.putExtra("clickedSweet",clickedSweet);
                startActivity(intent);
            }
        });
    }

    public void goToPusrchase(View view){
        Intent intent = new Intent(MenuActivity.this, PurchaseActivity.class);
        startActivity(intent);
    }

}
