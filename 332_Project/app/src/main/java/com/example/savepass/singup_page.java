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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class singup_page extends AppCompatActivity {

    private static final String TAG = "signup_page";

    DBconnection DB;
    private Button s_done;
    private EditText suser,smail,spass,scpass;
//    private TextInputLayout suser;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_page);

        s_done=(Button) findViewById(R.id.signup_done);
        smail = (EditText) findViewById(R.id.editTextTextPersonName);
        suser =(EditText) findViewById(R.id.username);
        spass = (EditText) findViewById(R.id.editTextTextPersonName2);
        scpass = (EditText) findViewById(R.id.editTextTextPassword3);

        smail.addTextChangedListener(signuptw);
        suser.addTextChangedListener(signuptw);
        spass.addTextChangedListener(signuptw);
        scpass.addTextChangedListener(signuptw);

        sharedPreferences=getSharedPreferences("loginusername",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        DB = new DBconnection(this);

        s_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Suser,Smail,Spass,Scpass;
                Suser = suser.getText().toString();
                Smail = smail.getText().toString();
                Spass = spass.getText().toString();
                Scpass = scpass.getText().toString();


                    if (suser.length() != 0 && smail.length() != 0 && spass.length() != 0 && scpass.length() != 0) {
                        if(validateUsername(Suser)==true & validatepassword(Spass)==true) {
                        if (Spass.equals(Scpass)) {
                            AddData(Suser, Smail, Spass);
                            /*suser.setText("");
                            smail.setText("");
                            spass.setText("");
                            scpass.setText("");*/
                        }
                        else {
                            toastMessage("Password didn't match!!!");
                        }
                        }
                    }
                    else {
                        toastMessage("Empty fields aren't allowed!");
                    }

                /*Intent l=new Intent(singup_page.this, login_page.class);
                startActivity(l);*/
            }
        });
    }

    private TextWatcher signuptw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String Suser,Smail,Spass,Scpass;
            Suser = suser.getText().toString();
            Smail = smail.getText().toString();
            Spass = spass.getText().toString();
            Scpass = scpass.getText().toString();

            s_done.setEnabled(!Suser.isEmpty() && !Smail.isEmpty() && !Spass.isEmpty() && ! Scpass.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public boolean validateUsername(String u)
    {
//        String u=suser.getText().toString().trim();
        boolean dt = DB.isValueExist(u);
        if (u.length()==0)
        {
            suser.setError("Field can't be empty");
            suser.setFocusable(true);
            suser.setFocusableInTouchMode(true);
            suser.requestFocus();
            return false;
        }
        else if(dt){
            suser.setError("Username already exists");
            return false;
        }
        else
        {
            suser.setError(null);
            //suser.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validatepassword(String p)
    {
//
        if (p.length()==0)
        {
            spass.setError("Field can't be empty");
            spass.setFocusable(true);
            spass.setFocusableInTouchMode(true);
            spass.requestFocus();
            return false;
        }
        else if(p.length()<8){
            spass.setError("Password should be minimum 8 character long");
            spass.setFocusable(true);
            spass.setFocusableInTouchMode(true);
            spass.requestFocus();
            spass.setSelection(spass.getText().length());
            return false;
        }
        else
        {
            spass.setError(null);
            //suser.setErrorEnabled(false);
            return true;
        }
    }

    private void signup()
    {
        String username=suser.getText().toString();
        String email=smail.getText().toString();
        if(username!="" && email!=""){
            editor.putBoolean("saveusername",true);
            editor.putString("username",username);
            editor.putString("email",email);
            editor.commit();}
    }


    public void AddData(String u,String e,String p) {
        boolean insertData = DB.addData(u,e,p);

        if (insertData) {
//            toastMessage("Signup Successfull.");
            signup();
            Intent l=new Intent(singup_page.this, login_page.class);
            l.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            l.putExtra("id",1);
            startActivity(l);
            smail.setError(null);
        } else {
            smail.setError("Email already exists");
            smail.setFocusable(true);
            smail.setFocusableInTouchMode(true);
            smail.requestFocus();
            smail.setSelection(smail.getText().length());
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}