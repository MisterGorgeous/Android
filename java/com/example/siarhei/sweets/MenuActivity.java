package com.example.siarhei.sweets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends Activity{
    private List<Sweet> sweets = new ArrayList<Sweet>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        addSwets();
        populateListView();
        registerClickCallback();
    }

    private void addSwets(){
        sweets.add(new Sweet(getResources().getString(R.string.swBall),2,R.drawable.sweet1,R.raw.blackcho));
        sweets.add(new Sweet(getResources().getString(R.string.swWhite),2,R.drawable.cho3,R.raw.choball));
        sweets.add(new Sweet(getResources().getString(R.string.swChocolate),10,R.drawable.chocolate,R.raw.whitecho));
    }

    private void populateListView() {
        ArrayAdapter<Sweet> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
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

    private class MyListAdapter extends ArrayAdapter<Sweet> {
        public MyListAdapter() {
            super(MenuActivity.this, R.layout.sweet, sweets);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.sweet, parent, false);
            }

            // Find the car to work with.
            Sweet thisWeet = sweets.get(position);

            // Fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView2);
            imageView.setImageResource(thisWeet.getIconId());

            // Make:
            TextView makeText = (TextView) itemView.findViewById(R.id.textView2);
            makeText.setText(thisWeet.getName());

            // Year:
            TextView yearText = (TextView) itemView.findViewById(R.id.textView3);
            yearText.setText(String.valueOf(thisWeet.getPrice()));

            return itemView;
        }
    }
}
