package com.example.toolapp.BdHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.toolapp.model.Admin;

import java.util.ArrayList;

public class AdminDB extends SQLiteOpenHelper {
    private static final String DATABASE = "bdadmin";
    private static final int VERSION = 1;
    public AdminDB(Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String admin = "CREATE TABLE admins(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeAdmin TEXT NOT NULL, senhaAdmin TEXT NOT NULL);";
        db.execSQL(admin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String admin = "DROP TABLE IF EXISTS admins";
        db.execSQL(admin);
    }

    public void salvarAdmin(Admin admin){
        ContentValues values = new ContentValues();
        values.put("nomeAdmin",admin.getNomeAdmin());
        values.put("senhaAdmin",admin.getSenhaAdmin());

        getWritableDatabase().insert("admins",null,values);
    }

    public void alterarAdmin(Admin admin){
        ContentValues values = new ContentValues();
        values.put("nomeAdmin",admin.getNomeAdmin());
        values.put("senhaAdmin",admin.getSenhaAdmin());

        String [] args = {String.valueOf(admin.getId())};
        getWritableDatabase().update("admins",values,"id=?",args);
    }

    public void deletarAdmin(Admin admin){
        String [] args = {String.valueOf(admin.getId())};
        getWritableDatabase().delete("admins","id=?",args);
    }

    public ArrayList<Admin> getListaAdmin(){
        String [] columns = {"id","nomeAdmin", "senhaAdmin"};
        Cursor cursor = getWritableDatabase().query("admins",columns,null,null,null,null,null,null);
        ArrayList<Admin> admin = new ArrayList<Admin>();

        while(cursor.moveToNext()){
            Admin admin2 = new Admin();
            admin2.setId(cursor.getLong(0));
            admin2.setNomeAdmin(cursor.getString(1));
            admin2.setSenhaAdmin(cursor.getString(2));
            admin.add(admin2);
        }

        return admin;
    }
}
