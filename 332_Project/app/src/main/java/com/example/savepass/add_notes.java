package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_notes extends AppCompatActivity {
    private Toolbar toolbar;
    private static final String TAG = "add_notes";

    DBconnection DB;
    private EditText title, note;
    private Button save;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogininfo;
    String userN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        Toolbar toolbar = findViewById(R.id.addnote_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        save = (Button) findViewById(R.id.button4);
        title = (EditText) findViewById(R.id.item_name);
        note = (EditText) findViewById(R.id.add_note);

        DB = new DBconnection(this);

        sharedPreferences=getSharedPreferences("loginusername",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        savelogininfo=sharedPreferences.getBoolean("saveusername",true);
        if (savelogininfo==true)
        {
            userN = sharedPreferences.getString("username",null);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ntitle, nnote;
                ntitle = title.getText().toString();
                nnote = note.getText().toString();


                if (title.length() != 0 && note.length() != 0) {
                    AddNote(ntitle, nnote,userN);
                    title.setText("");
                    note.setText("");
                } else {
                    toastMessage("Empty fields aren't allowed!");
                }
            }

        });
    }

    public void AddNote(String t, String u,String UN) {
        boolean insertData = DB.addnote(t, u,UN);

        if (insertData) {
//            toastMessage("Successful");
            Intent intent=new Intent(add_notes.this, add_items.class);
            startActivity(intent);
            finish();
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}