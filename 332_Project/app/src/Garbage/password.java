package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class password extends AppCompatActivity {
    private Toolbar toolbar;
    DBconnection DB;
    private  ListView lv_pass1;
    private Cursor dt;
    FloatingActionButton f_btn1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogininfo;
    String userN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        Toolbar toolbar= findViewById(R.id.pass_bar2);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Password");

        lv_pass1 = findViewById(R.id.lv_pass);
        DB = new DBconnection(this);

        sharedPreferences=getSharedPreferences("loginusername",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        savelogininfo=sharedPreferences.getBoolean("saveusername",true);
        if (savelogininfo==true)
        {
            userN = sharedPreferences.getString("username",null);
        }

        dt = DB.getDBPass(userN);
        if(dt.moveToFirst()){
            Fill(dt);
        }

        lv_pass1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                String I = String.valueOf(i);
                int L = I.length();
                L+=2;
                String title = name.substring(L);

                Intent editScreenIntent = new Intent(password.this, viewpass.class);
                editScreenIntent.putExtra("title",title);
                startActivity(editScreenIntent);
            }
        });

        f_btn1=(FloatingActionButton) findViewById(R.id.fab1);

        f_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(password.this, add_pass.class);
                startActivity(l);
            }
        });

    }
    public void Fill(Cursor dtb){

        ArrayList<String> listData = new ArrayList<>();
        Integer i = 1;
        do{
            //get the value from the database in column 0
            //then add it to the ArrayList
            String s = dtb.getString(0);
            String a = Integer.toString(i);
            String b = a + ": " + s;
            //toastMessage(b);
            listData.add(b);
            i++;
        }while(dtb.moveToNext());
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lv_pass1.setAdapter(adapter);
        dtb.close();
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}