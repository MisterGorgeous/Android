package com.example.siarhei.sweets;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends SettingsActivity {

    private static final String LOG_TAG = "menuLogs:";
    /*protected ArrayList<Sweet> cockies;
    protected ArrayList<Sweet> chocolate;*/
    private ArrayList<ArrayList<Sweet>> groups;

    public MenuActivity() {
        super( R.layout.menu_layout, R.id.expandableListView );

        context = MenuActivity.this;

        setQuery(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExpandableListView listView = (ExpandableListView)findViewById(R.id.expandableListView);
        ArrayList<ArrayList<Sweet>> groups = new ArrayList<ArrayList<Sweet>>();
        ArrayList<Sweet> chocolate = new ArrayList<Sweet>();
        ArrayList<Sweet> cockies = new ArrayList<Sweet>();

        setQuery(typesOfSweets.CHOCOLATE);
        addSwets(chocolate);
        groups.add(chocolate);

        setQuery(typesOfSweets.COOKIES);
        addSwets(cockies);
        groups.add(cockies);

        MyExplicAdapter adapter = new MyExplicAdapter(context, groups);
        listView.setAdapter(adapter);

       // registerClickCallback();




       /* adapter = new MyListAdapter(context,sweets,list);
        adapter.populateListView();
        registerClickCallback();*/
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

    /*@Override
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
    }*/

    @Override
    void registerClickCallback() {

        ExpandableListView list = (ExpandableListView) findViewById(listId);

        list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Sweet clickedSweet = groups.get(i).get(i1);
                Intent intent = new Intent(context, ShowActivity.class);
                intent.putExtra("clickedSweet",clickedSweet);
                startActivity(intent);
                return false;
            }
        });

        list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                return false;
            }
        });

    }

    @Override
    void countTotal(){}
}