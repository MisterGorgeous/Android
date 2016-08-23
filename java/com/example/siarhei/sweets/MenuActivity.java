package com.example.siarhei.sweets;

import android.app.Activity;
import android.content.Context;
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


public class MenuActivity extends SettingsActivity{


    public MenuActivity(){
        super( R.layout.menu_layout, R.id.listView, new String[]{"assortiment",null,null,null,null},null, null);
        context = MenuActivity.this;
    }

    public void goToPusrchase(View view){
        Intent intent = new Intent(MenuActivity.this, PurchaseActivity.class);
        startActivity(intent);
    }

}
