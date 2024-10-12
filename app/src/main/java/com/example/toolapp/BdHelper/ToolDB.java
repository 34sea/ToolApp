package com.example.toolapp.BdHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.toolapp.model.Admin;
import com.example.toolapp.model.Tool;

import java.util.ArrayList;

public class ToolDB extends SQLiteOpenHelper {
    private static final String DATABASE = "bdtool";
    private static final int VERSION = 1;
    public ToolDB(Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String admin = "CREATE TABLE tools(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeTool TEXT NOT NULL, descricaoTool TEXT NOT NULL, categoriaTool TEXT NOT NULL, dataTool TEXT NOT NULL, urlImgTool TEXT NOT NULL, quantidadeTool INTEGER NOT NULL, precoTool INTEGER NOT NULL, alugado TEXT NOT NULL);";
        db.execSQL(admin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String admin = "DROP TABLE IF EXISTS tools";
        db.execSQL(admin);
    }
    public void salvarTools(Tool tool){
        ContentValues values = new ContentValues();
        values.put("nomeTool",tool.getNomeTool());
        values.put("descricaoTool",tool.getDescricaoTool());
        values.put("categoriaTool",tool.getCategoriaTool());
        values.put("dataTool",tool.getDataTool());
        values.put("urlImgTool",tool.getUrlImgTool());
        values.put("precoTool",tool.getPrecoTool());
        values.put("quantidadeTool",tool.getQuantidadeTool());
        values.put("alugado",tool.getAlugado());
        getWritableDatabase().insert("tools",null,values);
    }

    public void alterarTools(Tool tool){
        ContentValues values = new ContentValues();
        values.put("nomeTool",tool.getNomeTool());
        values.put("descricaoTool",tool.getDescricaoTool());
        values.put("categoriaTool",tool.getCategoriaTool());
        values.put("dataTool",tool.getDataTool());
        values.put("urlImgTool",tool.getUrlImgTool());
        values.put("precoTool",tool.getPrecoTool());
        values.put("quantidadeTool",tool.getQuantidadeTool());
        values.put("alugado",tool.getAlugado());

        String [] args = {String.valueOf(tool.getId())};
        getWritableDatabase().update("tools",values,"id=?",args);
    }

    public void deletarTools(Tool tool){
        String [] args = {String.valueOf(tool.getId())};
        getWritableDatabase().delete("tools","id=?",args);
    }

    public ArrayList<Tool> getListaTools(){
        String [] columns = {"id","nomeTool", "descricaoTool", "categoriaTool","dataTool", "urlImgTool", "precoTool", "quantidadeTool", "alugado"};
        Cursor cursor = getWritableDatabase().query("tools",columns,null,null,null,null,null,null);
        ArrayList<Tool> admin = new ArrayList<Tool>();

        while(cursor.moveToNext()){
            Tool admin2 = new Tool();
            admin2.setId(cursor.getLong(0));
            admin2.setNomeTool(cursor.getString(1));
            admin2.setDescricaoTool(cursor.getString(2));
            admin2.setCategoriaTool(cursor.getString(3));
            admin2.setDataTool(cursor.getString(4));
            admin2.setUrlImgTool(cursor.getString(5));
            admin2.setPrecoTool(cursor.getInt(6));
            admin2.setQuantidadeTool(cursor.getInt(7));
            admin2.setAlugado(cursor.getString(8));
            admin.add(admin2);
        }

        return admin;
    }
}

