package com.example.toolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toolapp.BdHelper.AdminDB;
import com.example.toolapp.BdHelper.UserDB;
import com.example.toolapp.model.Admin;
import com.example.toolapp.model.UserModel;

import java.util.ArrayList;

public class TesteList extends AppCompatActivity {
    ListView lista;
    UserDB bdHelp;
    ArrayList<UserModel> listaP2;
    UserModel socio;
    ArrayAdapter adapter;
    private Button btnIr;
    private TextView txtA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_list);

        lista = (ListView) findViewById(R.id.testeUsers);
        txtA = findViewById(R.id.txto);

    }


    protected void onResume() {
        super.onResume();
        carregarListaSocio();
    }

    public void carregarListaSocio() {
        bdHelp = new UserDB(TesteList.this);
        listaP2 = bdHelp.getListaUser();
        bdHelp.close();
        if (listaP2 != null) {
            adapter = new ArrayAdapter<UserModel>(TesteList.this, android.R.layout.simple_list_item_1, listaP2);
            lista.setAdapter(adapter);
//            txtA.setText(listaP2.get(0).getNome());
        } else {
            txtA.setText("Vazio");
        }
    }

}
