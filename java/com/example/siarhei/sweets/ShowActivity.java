package com.example.siarhei.sweets;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.siarhei.sweets.bd.BDSweets;

import java.io.IOException;
import java.io.InputStream;

public class ShowActivity extends Activity {
    private Sweet clickedSweet;
    private BDSweets dbSweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);

        Intent intent = getIntent();

        ImageView image = (ImageView) findViewById(R.id.extraPict);
        TextView textView = (TextView) findViewById(R.id.extraInfo);
        TextView price = (TextView) findViewById(R.id.extraPrice);
        clickedSweet = (Sweet) intent.getSerializableExtra("clickedSweet");

        image.setImageResource(clickedSweet.getIconId());
        price.setText(String.valueOf(clickedSweet.getPrice()));



        InputStream file = getResources().openRawResource(clickedSweet.getExtraInfo());
        byte strInBytes[] = {};

        try {
            strInBytes = new byte[file.available()];
            file.read(strInBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText(new String(strInBytes));
    }

    public void backToMenu(View view){
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    public void putSweetInTheBusket(View view){
        dbSweets = new BDSweets(this);
        SQLiteDatabase db = dbSweets.getWritableDatabase();
        dbSweets.addSweet(db,clickedSweet);
        dbSweets.close();
    }

}
