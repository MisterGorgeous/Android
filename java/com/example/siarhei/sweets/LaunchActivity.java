package com.example.siarhei.sweets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.idescout.sql.SqlScoutServer;


public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.launch_layout);
        goToMainAcriviry();
       SqlScoutServer.create(this, getPackageName());
    }

    public void goToMainAcriviry(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
