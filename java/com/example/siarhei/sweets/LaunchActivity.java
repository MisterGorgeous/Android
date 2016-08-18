package com.example.siarhei.sweets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.launch_layout);

    }

   /* public void goToMainAcriviry(View view){
        Intent intent = new Intent(this,.class);
        startActivity(intent);
    }*/
}
