package com.example.studentbudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class database extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_USERS = "CREATE TABLE users( id INTEGER PRIMARY KEY AUTOINCREMENT,f_name TEXT,l_name TEXT,username TEXT,password TEXT,type INTEGER )";
    private static final String CREATE_TABLE_ORGNIZATIONS = "CREATE TABLE orgnizations( id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,contact TEXT,user_id INTEGER,address TEXT,image BLOB,type TEXT,email Text)";
    private static final String CREATE_TABLE_FEATURES = "CREATE TABLE features( id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,feature_desc TEXT,org_id INTEGER,image BLOB)";
    Context context;
    public static final String KEY_ROWID = "id";
    private static final String DATABASE_TABLE ="orgnizations";

    //    public static final String KEY_NAME = "beacon_name";
    private static final String dbname = "student.db";
    public database(@Nullable Context context) {
        super(context, dbname, null, 1);
        this.context = context;

    }

    public boolean insert_data(String fname,String lname,String username,String password,int usert_type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("f_name",fname);
        c.put("l_name",lname);
        c.put("username",username);
        c.put("password",password);
        c.put("type",usert_type);
        long r= db.insert("users",null,c);
        if(r== -1) return false;
        else
            return true;
    }
    public boolean insert_org(String name,String contact,String address,byte[] img,String type, int user_id,String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put("name",name);
        c.put("contact",contact);
        c.put("user_id",user_id);
        c.put("address",address);
        c.put("image",img);
        c.put("type",type);
        c.put("email",email);
        long r= db.insert("orgnizations",null,c);
        if(r== -1) return false;
        else
            return true;
    }
    public Cursor login(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from users where username = "+username+" and password= "+password, null);
        return cursor;
    }

    public Cursor readalldata()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String qry;
        qry="select * from orgnizations ";
        Cursor cursor=db.rawQuery(qry,null);
        return  cursor;
    }
    public Cursor OrgSingle( int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String qry;
        qry="select * from orgnizations where user_id="+id ;
        Cursor cursor=db.rawQuery(qry,null);
        return  cursor;
    }
    public Integer DeleteOrg( int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
       return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + id, null);
    }


    public boolean insert_feature(String title, String description,int org_id,byte[] img){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cont = new ContentValues();
        cont.put("title",title);
        cont.put("feature_desc",description);
        cont.put("org_id",org_id);
        cont.put("image",img);
        long r= db.insert("features",null,cont);
        if(r== -1) return false;
        else
            return true;
    }
    public Cursor OrgFeature( int org_id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String qry;
        qry="select * from features where org_id="+org_id ;
        Cursor cursor=db.rawQuery(qry,null);
        return  cursor;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ORGNIZATIONS);
        db.execSQL(CREATE_TABLE_FEATURES);
//        db.execSQL("DROP TABLE users");
//        db.execSQL("DROP TABLE orgnizations");
//        onCreate(db);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  users");
        db.execSQL("DROP TABLE IF EXISTS orgnizations");
        db.execSQL("DROP TABLE IF EXISTS features");
        onCreate(db);
    }
}

