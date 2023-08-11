package com.example.savepass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
//import android.widget.Toast;

//import androidx.annotation.Nullable;

public class DBconnection extends SQLiteOpenHelper {
    private static final String TAG = "DBcon";


    /*
     *Database Name-----------
     */
    private static final String DBNAME = "Safepass";


    private static final String LOGIN_TABLE = "Login";
    private static final String Email = "L_Email";
    private static final String Username = "L_Username";
    private static final String MPass = "L_password";


    private static final String PASS_TABLE = "Password";
    private static final String PTitle = "ptitle";
    private static final String PURL = "purl";
    private static final String PUSERNAME = "pusername";
    private static final String PPASS = "ppass";
    private static final String PUsername = "pun";

    private static final String NOTE_TABLE = "Notes";
    private static final String NTITLE = "ntitle";
    private static final String NNOTES = "nnotes";
    private static final String NUsername = "nun";

    private static final String ADDRESS_TABLE = "Address";
    private static final String ATITLE = "atitle";
    private static final String ANAME = "aname";
    private static final String AMOBILE = "amobile";
    private static final String AEMAIL = "aemail";
    private static final String AADD1 = "aadd1";
    private static final String AADD2 = "aadd2";
    private static final String AUsername = "aun";


    public DBconnection(Context context) {
        super(context, DBNAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ct;
        ct = "CREATE TABLE "+LOGIN_TABLE+" ("+Email+" text primary key not null,"+Username+" text not null UNIQUE,L_password text not null)";
        db.execSQL(ct);

        String ct1;
        ct1 = "CREATE TABLE "+PASS_TABLE+" ("+PTitle+" text primary key not null,purl text not null,pusername text not null,ppass text not null,pun text not null)";
        db.execSQL(ct1);

        String ct2;
        ct2 = "CREATE TABLE "+NOTE_TABLE+" ("+NTITLE+" text primary key not null,nnotes text not null,nun text not null)";
        db.execSQL(ct2);

        String ct3;
        ct3 = "CREATE TABLE "+ADDRESS_TABLE+" ("+ATITLE+" text primary key not null,aname text not null,amobile text not null,aemail text not null,aadd1 text not null,aadd2 text not null,aun text not null)";
        db.execSQL(ct3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+LOGIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+PASS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+NOTE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ADDRESS_TABLE);

        onCreate(db);
    }


    /*
     * This are the insert methods for our project-------------
     */
    public boolean addData(String u,String e,String p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Username,u);
        cv.put(Email,e);
        cv.put(MPass,p);

        //Log.d(TAG,"Adding: "+u+" and "+e+" and "+p+" to"+LOGIN_TABLE);

        long inserted = db.insert(LOGIN_TABLE,null,cv);
        //db.close();
        if(inserted!=-1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addpass(String t,String u,String n,String p,String un){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PTitle,t);
        cv.put(PURL,u);
        cv.put(PUSERNAME,n);
        cv.put(PPASS,p);
        cv.put(PUsername,un);

        //Log.d(TAG,"Adding: "+t+" and "+u+"  to"+LOGIN_TABLE);

        long inserted = db.insert(PASS_TABLE,null,cv);
        //db.close();
        if(inserted!=-1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addnote(String t,String n,String un){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NTITLE,t);
        cv.put(NNOTES,n);
        cv.put(NUsername,un);


        //Log.d(TAG,"Adding: "+t+" and "+u+"  to"+LOGIN_TABLE);

        long inserted = db.insert(NOTE_TABLE,null,cv);
        //db.close();
        if(inserted!=-1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addaddress(String t,String n,String m,String e,String add1,String add2,String un){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ATITLE,t);
        cv.put(ANAME,n);
        cv.put(AMOBILE,m);
        cv.put(AEMAIL,e);
        cv.put(AADD1,add1);
        cv.put(AADD2,add2);
        cv.put(AUsername,un);

        //Log.d(TAG,"Adding: "+t+" and "+u+"  to"+LOGIN_TABLE);

        long inserted = db.insert(ADDRESS_TABLE,null,cv);
        //db.close();

        if(inserted!=-1){
            return true;
        }
        else{
            return false;
        }
    }

    //===============================================**************************************=================================//
    //                                                 SELECT METHODS                                                       //
    //================================================**************************************================================//

    public Cursor getDBPass(String un){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PASS_TABLE + " WHERE pun = \""+un+"\"";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }
    public Cursor getDBNote(String un){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + NOTE_TABLE + " WHERE nun = \""+un+"\"";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }
    public Cursor getDBAdd(String un){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ADDRESS_TABLE + " WHERE aun = \""+un+"\"";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }


    public Cursor getPass(String n,String un){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PASS_TABLE +" Where ptitle = \""+n+"\" and pun = \""+un+"\"";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }
    public Cursor getNote(String n,String un){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + NOTE_TABLE +" Where ntitle = \"" + n + "\" and nun = \""+un+"\"";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }
    public Cursor getAdd(String n,String un){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ADDRESS_TABLE +" Where atitle = \""+n+"\" and aun = \""+un+"\"";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }
      //  =======================================================================***************************************************============================  //
     //                                                                                    Boolean Section                                                       //
    //  ========================================================================***************************************************===========================  //

    public boolean isLogin(String e,String p){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Login Where L_Email = \""+e+"\" or L_Username = \""+e+"\"";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToFirst()){
            do{
                String pa;
                //em = data.getString(0);
                pa = data.getString(2);
                //e.equals(em) &&
                if(p.equals(pa)){
                    return true;
                }

            }while(data.moveToNext());
            return false;
        }
        else{
            return false;
        }
    }

    public boolean isValueExist(String value){
          String query = "SELECT * FROM Login WHERE L_Username = \""+value+"\"";
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor data = db.rawQuery(query, null);
          //db.close();
          if(data.moveToFirst())
          {
              return true;
          }
          else{
              return false;
          }
      }

    public boolean isLog(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Login";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        if(data.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    //  =======================================================================***************************************************============================  //
    //                                                                                    UPDATE SECTION                                                        //
    //  ========================================================================***************************************************===========================  //

    public void UpdatePass(cPass pa,String un){
        String t,ur,us,p;
        t = pa.getTitle();
        ur = pa.getUrl();
        us = pa.getUsername();
        p = pa.getPass();
        String q = "UPDATE "+PASS_TABLE+" SET purl = \""+ur+"\", pusername = \""+us+"\", ppass = \""+p+"\" WHERE ptitle = \""+t+"\" and pun = \""+un+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }
    public void UpdateNote(cNote no,String un){
        String t,nt;
        t = no.getTitle();
        nt = no.getNote();
        String q = "UPDATE "+NOTE_TABLE+" SET nnotes = \""+nt+"\" WHERE ntitle = \""+t+"\" and nun = \""+un+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }
    public void UpdateAdd(cAdd ad,String un){
        String t,n,m,e,a1,a2;
        t = ad.getTitle();
        n = ad.getName();
        m = ad.getMobile();
        e = ad.getEmial();
        a1= ad.getAdd1();
        a2= ad.getAdd2();
        String q = "UPDATE "+ADDRESS_TABLE+" SET aname = \""+n+"\", amobile = \""+m+"\", aemail = \""+e+"\",aadd1 = \""+a1+"\", aadd2 = \""+a2+"\" WHERE atitle = \""+t+"\" and aun = \""+un+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }
    public void ChangePass(String un,String np){
        String q = "UPDATE "+LOGIN_TABLE+" SET L_password = \""+np+"\" WHERE L_Username = \""+un+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }

    //========================================================***********************************==================================//
    //                                                 Shared pref er jonno                                                        //
    //==========================================================**********************************=================================//

    public Cursor GetNE(String s){
        String q = "Select * from Login Where L_Email = \""+s+"\" or L_Username = \""+s+"\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dt = db.rawQuery(q,null);
        return dt;
    }


    //========================================================***********************************==================================//
    //                                                 Delete Methods                                                              //
    //==========================================================**********************************=================================//

    public void DeletePass(String t,String un){
        String q = "DELETE FROM "+PASS_TABLE+" WHERE ptitle = \""+t+"\" and pun = \""+un+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }
    public void DeleteNote(String t,String un){
        String q = "DELETE FROM "+NOTE_TABLE+" WHERE ntitle = \""+t+"\" and nun = \""+un+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }
    public void DeleteAdd(String t,String un){
        String q = "DELETE FROM "+ADDRESS_TABLE+" WHERE atitle = \""+t+"\" and aun = \""+un+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }




}