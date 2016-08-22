package com.example.siarhei.sweets;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.siarhei.sweets.bd.BDSweets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PurchaseActivity extends Activity {
    private static final String LOG_TAG = "logs:PurchaseActivity";
    private List<Sweet> selectedSweets = new ArrayList<Sweet>();
    private BDSweets dbSweets;
    private ListView list;
    private MyListAdapter adapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);
        list = (ListView) findViewById(R.id.purchaselistView);

        addSwets();

        adapter = new MyListAdapter(PurchaseActivity.this,selectedSweets,list);
        adapter.populateListView();
        registerClickCallback();

    }

    private void addSwets(){

        dbSweets = new BDSweets(this);
        db = dbSweets.getWritableDatabase();
        Cursor c =  db.query("selectedSweets", null, null, null, null, null, null);

     //   selectedSweets.add(new Sweet(getResources().getString(R.string.swBall),(float)10.5,R.drawable.sweet1,R.raw.blackcho,0));

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameIndex = c.getColumnIndex("name");
            int iconIndex = c.getColumnIndex("icon");
            int infoIndex = c.getColumnIndex("info");
            int priceIndex = c.getColumnIndex("price");
            int typeOfSweets = c.getColumnIndex("typesofsw");

            do {
                selectedSweets.add(new Sweet(c.getString(nameIndex),c.getFloat(priceIndex),c.getInt(iconIndex),c.getInt(infoIndex),c.getInt(idColIndex), c.getInt(typeOfSweets)));
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();

        countTotal();
    }



    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.purchaselistView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                Sweet clickedSweet = selectedSweets.get(position);
                selectedSweets.remove(clickedSweet);
                dbSweets.deleteSweet(db,clickedSweet.getTableIndex());
                adapter.notifyDataSetChanged();
                countTotal();

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


    private void countTotal(){
        float res = 0;

        if(!selectedSweets.isEmpty()){
            Iterator<Sweet> sweetsList = selectedSweets.iterator();
            while (sweetsList.hasNext()) {
                res += sweetsList.next().getPrice();
            }
        }

        TextView totalView  = (TextView) findViewById(R.id.totalView);
        totalView.setText("Total: " +  String.valueOf(res));
    }
}
