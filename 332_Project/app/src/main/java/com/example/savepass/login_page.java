package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
    Button l_done;
    EditText lmail,lpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CheckBox rem;
    DBconnection db;
    TextView na;
    Boolean savelogininfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        db = new DBconnection(this);

        l_done = findViewById(R.id.login_done);
        lmail = findViewById(R.id.editTextTextEmailAddress);
        lpass = findViewById(R.id.editTextTextPassword);


        lmail.addTextChangedListener(logintextwatcher);
        lpass.addTextChangedListener(logintextwatcher);

        sharedPreferences=getSharedPreferences("loginusername",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        na= findViewById(R.id.newaccount);

        savelogininfo=sharedPreferences.getBoolean("saveusername",true);
        if (savelogininfo==true)
        {
            lmail.setText(sharedPreferences.getString("username",null));
            lpass.setFocusable(true);
            lpass.setFocusableInTouchMode(true);
            lpass.requestFocus();
        }
        else
        {
            lmail.setFocusable(true);
            lmail.setFocusableInTouchMode(true);
            lmail.requestFocus();
        }

        na.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                Intent in=new Intent(login_page.this, singup_page.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            }
        });


        l_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login();
                String em,pa;
                em = lmail.getText().toString();
                pa =lpass.getText().toString();
                //toastMessage(em + pa);
                boolean in = db.isLogin(em,pa);
                //toastMessage(in == true?"true":"false");
                if(in){
//                    toastMessage("Login Successful...");
                    Intent lo = getIntent();
                    int ok = lo.getIntExtra("id",-1);
                    login();
                    if(ok==1){
                        Intent l=new Intent(login_page.this, Setup_done.class);
                        l.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(l);
                    }
                    else{
                        Intent l=new Intent(login_page.this, Homepage_Version2.class);
                        l.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(l);
                    }
                }
                else{
                    toastMessage("Email or Password doesn't match");
                }

            }
        });

    }
    private TextWatcher logintextwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mail = lmail.getText().toString();
                String pass = lpass.getText().toString();

                l_done.setEnabled(!mail.isEmpty() && !pass.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private void logout()
    {
        editor.putBoolean("saveusername",false);
        editor.putBoolean("email",false);
        editor.clear();
        editor.commit();
    }

    private void login()
    {
        String username=lmail.getText().toString();
        /*if(username!=""){
            editor.putBoolean("saveusername",true);
        editor.putString("username",username);
        editor.commit();}*/
        Cursor c = db.GetNE(username);
        String us,em;
        us = "";
        em = "";
        if(c.moveToFirst()){
            do{
                us = c.getString(1);
                em = c.getString(0);
            }while(c.moveToNext());
        }
        editor.putBoolean("saveusername",true);
        editor.putString("username",us);
        editor.putString("email",em);
        editor.commit();

    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}