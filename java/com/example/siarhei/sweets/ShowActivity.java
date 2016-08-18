package com.example.siarhei.sweets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class ShowActivity extends Activity {
    private Button buy;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_layout);

    }
}
