package com.example.toolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toolapp.BdHelper.ToolDB;
import com.example.toolapp.model.Tool;
import com.example.toolapp.model.UserModel;

import java.util.ArrayList;

public class Loja extends AppCompatActivity {

    ListView lista;
    ToolDB bdHelp;
    ArrayList<Tool> listaP2;
    Tool tool;
    UserModel userHome;
    ArrayAdapter adapter;
    private Button btnIr;
    private TextView txtA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);
//        btnAddTool = findViewById(R.id.btnAddTools);
        txtA = findViewById(R.id.txto);
        lista = (ListView) findViewById(R.id.testeUsers);

        Intent userTake = getIntent();
        userHome = (UserModel) userTake.getSerializableExtra("user");

        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                Tool s = (Tool) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(Loja.this, DetalhesTools.class);
                intent.putExtra("too", s);
                intent.putExtra("user", userHome);
                startActivity(intent);
            }
        });

    }

    protected void onResume() {
        super.onResume();
        carregarListaTool();
    }

    public void carregarListaTool() {
        bdHelp = new ToolDB(Loja.this);
        listaP2 = bdHelp.getListaTools();
        bdHelp.close();
        if (listaP2 != null) {

            adapter = new ArrayAdapter<Tool>(Loja.this, android.R.layout.simple_list_item_1, listaP2);
            lista.setAdapter(adapter);
            txtA.setText(String.valueOf(listaP2.size()));
        } else {
            txtA.setText("Vazio");
        }
    }
}