package com.example.siarhei.sweets;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MenuActivity extends SettingsActivity {

    private static final String LOG_TAG = "menuLogs:";

    public MenuActivity() {
        super( R.layout.menu_layout, R.id.listView );

        context = MenuActivity.this;

        setQuery(null);
    }

  /*  public MenuActivity(){
        super( R.layout.menu_layout, R.id.listView, new String[]{"assortiment",null,null,null,null},null, null);
    }*/


    public void goToPusrchase(View view){

        PurchaseActivity purchase = new PurchaseActivity();
        Intent intent = new Intent(MenuActivity.this, PurchaseActivity.class);
        startActivity(intent);
    }

    @Override
    void setQuery(typesOfSweets clickedSweet) {

        if(clickedSweet == null){
            this.queryParametrs = new String[]{"assortiment", null, null, null, null};
        }
        else {
            this.whereArgs = new String[]{Integer.toString(clickedSweet.ordinal() + 1)};
            this.queryParametrs = new String[]{"assortiment", "typesofsw = ?", null, null, null};
        }
        this.tableColums = null;

    }

    @Override
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

    @Override
    void countTotal(){}
}