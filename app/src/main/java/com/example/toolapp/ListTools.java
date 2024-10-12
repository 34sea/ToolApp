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
import com.example.toolapp.BdHelper.UserDB;
import com.example.toolapp.model.Tool;
import com.example.toolapp.model.UserModel;

import java.util.ArrayList;

public class ListTools extends AppCompatActivity {
    ListView lista;
    ToolDB bdHelp;
    ArrayList<Tool> listaP2;
    Tool tool;
    ArrayAdapter adapter;
    private Button btnIr;
    private TextView txtA;

    private TextView btnAddTool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tools);
        btnAddTool = findViewById(R.id.btnAddTools);
        txtA = findViewById(R.id.txto);
        lista = (ListView) findViewById(R.id.testeUsers);

        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                Tool s = (Tool) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(ListTools.this, AddTools.class);
                intent.putExtra("userProfile", s);
                startActivity(intent);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id){
                tool = (Tool) adapterView.getItemAtPosition(position);
                return false;
            }
        });


        btnAddTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTools.this, AddTools.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem menuDelete = menu.add("Apagar");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {

                bdHelp =  new ToolDB((ListTools.this));
                bdHelp.deletarTools(tool);
                bdHelp.close();
                Intent intent = new Intent(ListTools.this, ListTools.class);
                startActivity(intent);
                return true;
            }
        });
    }

    protected void onResume() {
        super.onResume();
        carregarListaTool();
    }

    public void carregarListaTool() {
        bdHelp = new ToolDB(ListTools.this);
        listaP2 = bdHelp.getListaTools();
        bdHelp.close();
        if (listaP2 != null) {
            adapter = new ArrayAdapter<Tool>(ListTools.this, android.R.layout.simple_list_item_1, listaP2);
            lista.setAdapter(adapter);
            txtA.setText(String.valueOf(listaP2.size()));
        } else {
            txtA.setText("Vazio");
        }
    }
}