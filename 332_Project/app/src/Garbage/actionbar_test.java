package com.example.savepass;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class actionbar_test extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar_test);
        toolbar=findViewById(R.id.actionbar_test2);

        setSupportActionBar(toolbar);
        toolbar.setTitle("test");

    }
}