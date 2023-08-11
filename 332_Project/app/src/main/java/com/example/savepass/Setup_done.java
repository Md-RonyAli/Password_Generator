package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setup_done extends AppCompatActivity {

    Button setup_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_done);

        setup_done=(Button) findViewById(R.id.s_done);

        setup_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(Setup_done.this, Homepage_Version2.class);
                startActivity(l);
            }
        });
    }
}