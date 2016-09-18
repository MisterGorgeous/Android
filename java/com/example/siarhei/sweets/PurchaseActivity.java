package com.example.siarhei.sweets;

import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PurchaseActivity extends SettingsActivity {


    public PurchaseActivity() {
        super(R.layout.purchase_layout, R.id.purchaselistView);

        context = PurchaseActivity.this;

        setQuery(null);
    }
    @Override
    public void setQuery(typesOfSweets clickedSweet){
        this.queryParametrs =  new String[]{"selectedSweets",null,null,null,null};
        this.tableColums = null;
        this.whereArgs = null;
       /* sweets = new ArrayList<Sweet>();
        addSwets();

        adapter = new MyListAdapter(context,sweets,list);
        adapter.populateListView();
        registerClickCallback();*/
    }

    @Override
    protected void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.purchaselistView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                List<Sweet> selectedSweets = getSweets();
                Sweet clickedSweet = selectedSweets.get(position);
                selectedSweets.remove(clickedSweet);
                dbSweets.deleteSweet(db,clickedSweet.getTableIndex());
                adapter.notifyDataSetChanged();
                countTotal();
               // dbSweets.close();
               // db.close();
            }
        });
    }

    public void goToMenu(View view){
        Intent intent = new Intent(PurchaseActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void buy(View view){
        Intent intent = new Intent(PurchaseActivity.this, AutorizationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void countTotal() {
        float res = 0;
        List<Sweet> sweets = getSweets();

        if(!sweets.isEmpty()){
            Iterator<Sweet> sweetsList = sweets.iterator();
            while (sweetsList.hasNext()) {
                res += sweetsList.next().getPrice();
            }
        }

        TextView totalView  = (TextView) findViewById(R.id.totalView);
        totalView.setText("Total: " +  String.valueOf(res));
    }


}