package com.example.siarhei.sweets;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.siarhei.sweets.bd.BDSweets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.io.IOException;
import java.io.InputStream;

public class ShowActivity extends Activity {
    private Sweet clickedSweet;
    private BDSweets dbSweets;
    private EditText ammount;
    private TextView total;
    private int currentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);

        Intent intent = getIntent();

        ImageView image = (ImageView) findViewById(R.id.extraPict);
        TextView textView = (TextView) findViewById(R.id.extraInfo);
        TextView price = (TextView) findViewById(R.id.showPrice);
        total = (TextView) findViewById(R.id.showTotal);
        ammount = (EditText) findViewById(R.id.amount);
        clickedSweet = (Sweet) intent.getSerializableExtra("clickedSweet");

        image.setImageResource(clickedSweet.getIconId());
        price.setText(String.valueOf(clickedSweet.getPrice()));

        ammount.addTextChangedListener(new TextWatcher(){
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

          @Override
          public void afterTextChanged(Editable editable) {
              String str = ammount.getText().toString();

               if(checkStr(str))
                  currentAmount = Integer.parseInt(str);
               else
                    currentAmount = 0;

              total.setText(String.valueOf(currentAmount * clickedSweet.getPrice()));
          }
      }
        );

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

    private boolean checkStr(String s) {
        Pattern p = Pattern.compile("^[0-9]{1,}$");
        Matcher m = p.matcher(s);
        return m.matches();

    }


    public void backToMenu(View view){
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    public void putSweetInTheBusket(View view){
        if(currentAmount > 0) {
            clickedSweet.setAmount(currentAmount);
            clickedSweet.setPrice(currentAmount * clickedSweet.getPrice());
            dbSweets = new BDSweets(this);
            SQLiteDatabase db = dbSweets.getWritableDatabase();
            dbSweets.addSweetInDB(db, clickedSweet);
            dbSweets.close();
        }
    }

}
