package com.example.siarhei.sweets;

import android.content.Intent;
import android.util.Log;
import android.view.View;


public class MenuActivity extends SettingsActivity{

    private static final String LOG_TAG = "menuLogs:";

    public MenuActivity(){//typesOfSweets clickedSweet) {
        super( R.layout.menu_layout, R.id.listView );

        context = MenuActivity.this;

        setQuery(new String[]{"assortiment",null,null,null,null},null, null);

        /*if(typesOfSweets.CHOCOLATE == clickedSweet)
            setQuery(new String[]{"assortiment",null,null,null,null},null, null);
        else if (typesOfSweets.COOKIES == clickedSweet)
            setQuery(new String[]{"assortiment",null,null,null,null},null, null);
        else
            Log.e(LOG_TAG, ": Error:No such sweet.---------");*/


    }

  /*  public MenuActivity(){
        super( R.layout.menu_layout, R.id.listView, new String[]{"assortiment",null,null,null,null},null, null);
    }*/

    public void goToPusrchase(View view){

        PurchaseActivity purchase = new PurchaseActivity();
        Intent intent = new Intent(MenuActivity.this, PurchaseActivity.class);
        startActivity(intent);
    }

}
