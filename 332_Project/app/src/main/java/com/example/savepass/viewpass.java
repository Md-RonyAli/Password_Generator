package com.example.savepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class viewpass extends AppCompatActivity {
    private Toolbar toolbar;
    DBconnection DB;
    private EditText title,url,username,pass;
    private Button edit,save,copy;
    private String pt;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogininfo;
    String userN;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpass);

        Toolbar toolbar= findViewById(R.id.viewpass_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPreferences=getSharedPreferences("loginusername",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        savelogininfo=sharedPreferences.getBoolean("saveusername",true);
        if (savelogininfo==true)
        {
            userN = sharedPreferences.getString("username",null);
        }

        title = findViewById(R.id.item_title);
        url = findViewById(R.id.pass_url);
        username = findViewById(R.id.editTextTextPersonName5);
        pass = findViewById(R.id.editTextTextPassword);
        edit = findViewById(R.id.button4);
        copy=(Button) findViewById(R.id.copy_btn);
        save= findViewById(R.id.save);
        save.setVisibility(View.INVISIBLE);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setVisibility(View.INVISIBLE);
                save.setVisibility(View.VISIBLE);
                EnableEditText(url);
                EnableEditText(username);
                EnableEditText(pass);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);

                cPass p = new cPass();
                p.setTitle(title.getText().toString());
                p.setUrl(url.getText().toString());
                p.setUsername(username.getText().toString());
                p.setPass(pass.getText().toString());

                DB.UpdatePass(p,userN);

                disableEditText(url);
                disableEditText(username);
                disableEditText(pass);
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t = pass.getText().toString();

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (!t.equals("")) {
                    ClipData clip = ClipData.newPlainText("edit", t);
                    clipboardManager.setPrimaryClip(clip);
                    toastMessage("Password Copied");
                } else {
                    toastMessage("Password Field is Empty");
                }
            }
        });

        DB = new DBconnection(this);

        Intent Ri = getIntent();
        pt = Ri.getStringExtra("title");

        cPass a = new cPass();
        Cursor dt = DB.getPass(pt,userN);
        if(dt.moveToFirst()){
            do{
                a.setTitle(dt.getString(0));
                a.setUrl(dt.getString(1));
                a.setUsername(dt.getString(2));
                a.setPass(dt.getString(3));
            }while(dt.moveToNext());
            title.setText(a.getTitle());
            disableEditText(title);
            url.setText(a.getUrl());
            disableEditText(url);
            username.setText(a.getUsername());
            disableEditText(username);
            pass.setText(a.getPass());
            disableEditText(pass);

        }else {
            toastMessage("Sorry no data has been found");
        }

    }

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
                        DB.DeletePass(s,userN);
                        Intent intent=new Intent(viewpass.this, Homepage_Version2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("id",1);
                        startActivity(intent);
                        finish();
                    }
                });
        //Dialog dialog=new Dialog();
        builder.show();
    }

    /*public void showPopup(View view)
    {
        PopupMenu popup=new PopupMenu(this,view);
        MenuInflater inflater=popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu,popup.getMenu());
        popup.show();
    }*/

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
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
}