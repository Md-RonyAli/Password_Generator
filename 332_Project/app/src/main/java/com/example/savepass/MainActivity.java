package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  static int sto=200;
    private DBconnection DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        DB = new DBconnection(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent;

                if(DB.isLog()==true){
                    homeIntent = new Intent(MainActivity.this, login_page.class);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    homeIntent.putExtra("id",2);
                    startActivity(homeIntent);
                }
                else{
                    homeIntent = new Intent(MainActivity.this, first_time_show.class);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    homeIntent.putExtra("id",1);
                    startActivity(homeIntent);
                }
            }
        },sto);
    }

}