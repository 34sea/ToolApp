package com.example.toolapp.BdHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.toolapp.model.Admin;
import com.example.toolapp.model.UserModel;

import java.util.ArrayList;

public class UserDB extends SQLiteOpenHelper {
    private static final String DATABASE = "bduser";
    private static final int VERSION = 1;
    public UserDB(Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String admin = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeAdmin TEXT NOT NULL, senhaAdmin TEXT NOT NULL, pagamento TEXT NOT NULL, dataUserPay TEXT NOT NULL, dataNac TEXT NOT NULL, nomeCompleto TEXT NOT NULL, genero TEXT NOT NULL, morada TEXT NOT NULL, telefone INTEGER NOT NULL);";
        db.execSQL(admin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String admin = "DROP TABLE IF EXISTS users";
        db.execSQL(admin);
    }
    public void salvarUser(UserModel admin){
        ContentValues values = new ContentValues();
        values.put("nomeAdmin",admin.getNomeAdmin());
        values.put("senhaAdmin",admin.getSenhaAdmin());
        values.put("pagamento",admin.getPagamento());
        values.put("dataUserPay",admin.getDataUserPay());
        values.put("dataNac",admin.getDataNac());
        values.put("nomeCompleto",admin.getNomeCompleto());
        values.put("genero",admin.getGenero());
        values.put("morada",admin.getMorada());
        values.put("telefone",admin.getTelefone());

        getWritableDatabase().insert("users",null,values);
    }

    public void alterarUser(UserModel admin){
        ContentValues values = new ContentValues();
        values.put("nomeAdmin",admin.getNomeAdmin());
        values.put("senhaAdmin",admin.getSenhaAdmin());
        values.put("pagamento",admin.getPagamento());
        values.put("dataUserPay",admin.getDataUserPay());
        values.put("dataNac",admin.getDataNac());
        values.put("nomeCompleto",admin.getNomeCompleto());
        values.put("genero",admin.getGenero());
        values.put("morada",admin.getMorada());
        values.put("telefone",admin.getTelefone());

        String [] args = {String.valueOf(admin.getId())};
        getWritableDatabase().update("users",values,"id=?",args);
    }

    public void deletarUser(UserModel admin){
        String [] args = {String.valueOf(admin.getId())};
        getWritableDatabase().delete("users","id=?",args);
    }

    public ArrayList<UserModel> getListaUser(){
        String [] columns = {"id","nomeAdmin", "senhaAdmin", "pagamento", "dataUserPay", "dataNac", "nomeCompleto", "genero", "morada", "telefone"};
        Cursor cursor = getWritableDatabase().query("users",columns,null,null,null,null,null,null);
        ArrayList<UserModel> admin = new ArrayList<UserModel>();

        while(cursor.moveToNext()){
            UserModel admin2 = new UserModel();
            admin2.setId(cursor.getLong(0));
            admin2.setNomeAdmin(cursor.getString(1));
            admin2.setSenhaAdmin(cursor.getString(2));
            admin2.setPagamento(cursor.getString(3));
            admin2.setDataUserPay(cursor.getString(4));
            admin2.setDataNac(cursor.getString(5));
            admin2.setNomeCompleto(cursor.getString(6));
            admin2.setGenero(cursor.getString(7));
            admin2.setMorada(cursor.getString(8));
            admin2.setTelefone(cursor.getInt(9));
            admin.add(admin2);
        }

        return admin;
    }
}

