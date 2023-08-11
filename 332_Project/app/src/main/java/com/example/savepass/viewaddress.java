package com.example.savepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class viewaddress extends AppCompatActivity {
private Toolbar toolbar;
    DBconnection DB;
    private EditText title,name,mobile,email,add1,add2;
    private Button edit,save;
    private String at;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogininfo;
    String userN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewaddress);

        Toolbar toolbar= findViewById(R.id.viewaddress_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        title = findViewById(R.id.item_title);
        name = findViewById(R.id.address_name);
        mobile = findViewById(R.id.address_phone);
        email = findViewById(R.id.address_email);
        add1 = findViewById(R.id.address_preaddress);
        add2 = findViewById(R.id.address_peraddress);
        edit = findViewById(R.id.button4);
        save=findViewById(R.id.save);

        save.setVisibility(View.INVISIBLE);


        sharedPreferences=getSharedPreferences("loginusername",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        savelogininfo=sharedPreferences.getBoolean("saveusername",true);
        if (savelogininfo==true)
        {
            userN = sharedPreferences.getString("username",null);
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setVisibility(View.INVISIBLE);
                save.setVisibility(View.VISIBLE);

                //EnableEditText(title);
                EnableEditText(name);
                EnableEditText(mobile);
                EnableEditText(email);
                EnableEditText(add1);
                EnableEditText(add2);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);

                cAdd add = new cAdd();
                add.setTitle(title.getText().toString());
                add.setName(name.getText().toString());
                add.setMobile(mobile.getText().toString());
                add.setEmial(email.getText().toString());
                add.setAdd1(add1.getText().toString());
                add.setAdd2(add2.getText().toString());


                DB.UpdateAdd(add,userN);


                //disableEditText(title);
                disableEditText(name);
                disableEditText(mobile);
                disableEditText(email);
                disableEditText(add1);
                disableEditText(add2);
            }
        });

        DB = new DBconnection(this);

        Intent Ri = getIntent();
        at = Ri.getStringExtra("title");

        cAdd a = new cAdd();
        Cursor dt = DB.getAdd(at,userN);
        if(dt.moveToFirst()){
            do{
                a.setTitle(dt.getString(0));
                a.setName(dt.getString(1));
                a.setMobile(dt.getString(2));
                a.setEmial(dt.getString(3));
                a.setAdd1(dt.getString(4));
                a.setAdd2(dt.getString(5));
            }while(dt.moveToNext());
            title.setText(a.getTitle());
            disableEditText(title);
            name.setText(a.getName());
            disableEditText(name);
            mobile.setText(a.getMobile());
            disableEditText(mobile);
            email.setText(a.getEmial());
            disableEditText(email);
            add1.setText(a.getAdd1());
            disableEditText(add1);
            add2.setText(a.getAdd2());
            disableEditText(add2);

        }else {
            toastMessage("Sorry no data has been found");
        }
    }

    /*public void showPopup(View view)
    {
        PopupMenu popup=new PopupMenu(this,view);
        MenuInflater inflater=popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu,popup.getMenu());
        popup.show();
        //getMenuInflater().inflate(R.menu.popup_menu,menu);
    }*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;*/
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete: {
                openDialog();
            }
            break;
        }return false;
    }
    public void openDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s=title.getText().toString();
                        DB.DeleteAdd(s,userN);
                        Intent intent=new Intent(viewaddress.this, Homepage_Version2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("id",3);
                        startActivity(intent);
                        finish();
                    }
                });
        //Dialog dialog=new Dialog();
        builder.show();
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);

    }
    private void EnableEditText(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.setEnabled(true);
        editText.setCursorVisible(true);
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}