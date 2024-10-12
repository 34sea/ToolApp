package com.example.toolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.toolapp.BdHelper.AdminDB;
import com.example.toolapp.BdHelper.ToolDB;
import com.example.toolapp.model.Admin;
import com.example.toolapp.model.Tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTools extends AppCompatActivity {
    private Spinner spinner;
    EditText nome, preco, desc;
    private TextView txtLog;
    private TextView btnA;
    Tool tool;
    ToolDB dbHelper;
    private String selectedItem;
    Tool editaTool, too, idTool;
    String cargoU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tools);

        tool = new Tool();
        dbHelper = new ToolDB(AddTools.this);

        Intent toolIntent = getIntent();

        editaTool = (Tool) toolIntent.getSerializableExtra("userProfile");
        cargoU = toolIntent.getStringExtra("cargo");
        idTool = (Tool) toolIntent.getSerializableExtra("perfil");

        spinner = findViewById(R.id.spnCategoria);
        btnA = findViewById(R.id.btnAddUser);
        nome = findViewById(R.id.editTextNome);
        preco = findViewById(R.id.editTextPreco);
        desc = findViewById(R.id.editTextDesc);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);


        String[] items = {"Escolha Categoria", "Martelo", "Faca"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(editaTool!=null || idTool!=null){
            btnA.setText("Modificar");
            if(cargoU!=null){
                if(cargoU.equals("pagamento")){
                    btnA.setText("Confirmar");
//                    pagH = "true";
                }
            }


            if(idTool!=null){

            }else{
                tool.setId(editaTool.getId());
                nome.setText(editaTool.getNomeTool());
                preco.setText(String.valueOf(editaTool.getPrecoTool()));
                desc.setText(editaTool.getDescricaoTool());

            }


        }else{
            btnA.setText("Cadastrar");
        }

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tool.setQuantidadeTool(4);
                tool.setPrecoTool(Integer.parseInt(preco.getText().toString()));
                tool.setDataTool(formattedDate.toString());
                tool.setCategoriaTool(selectedItem.toString());
                tool.setDescricaoTool(desc.getText().toString());
                tool.setNomeTool(nome.getText().toString());
                tool.setAlugado("false");
                tool.setUrlImgTool("img");

                if(btnA.getText().toString().equals("Cadastrar")) {
                    dbHelper.salvarTools(tool);
                    dbHelper.close();

                }else{
                    dbHelper.alterarTools(tool);
                    dbHelper.close();
                }

                screenTool();

            }
        });

    }

    public void screenTool(){
        Intent listaTools = new Intent(AddTools.this, ListTools.class);
        startActivity(listaTools);
    }
}