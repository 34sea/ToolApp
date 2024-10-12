package com.example.toolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.toolapp.BdHelper.ToolDB;
import com.example.toolapp.BdHelper.UserDB;
import com.example.toolapp.model.Tool;
import com.example.toolapp.model.UserModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DetalhesTools extends AppCompatActivity {
    private Tool t, tool;
    ToolDB dbHelper2;
    private UserModel us;
    TextView preco, nome, btnAlugar;
    UserModel admin;
    UserDB dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_tools);

        admin = new UserModel();
        dbHelper = new UserDB(DetalhesTools.this);

        tool = new Tool();
        dbHelper2 = new ToolDB(DetalhesTools.this);

        preco = findViewById(R.id.txtPreco);
        nome = findViewById(R.id.txtNome);
        btnAlugar = findViewById(R.id.btnAddUserAlugar);
        Intent take = getIntent();
        t = (Tool) take.getSerializableExtra("too");
        us = (UserModel) take.getSerializableExtra("user");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);

        if(t!=null){
            preco.setText(String.valueOf(t.getPrecoTool()));
            nome.setText(t.getNomeTool().toString());
        }

        btnAlugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t!=null) {
                    tool.setId(t.getId());
                    tool.setQuantidadeTool(4);
                    tool.setPrecoTool(t.getPrecoTool());
                    tool.setDataTool(t.getDataTool().toString());
                    tool.setCategoriaTool(t.getCategoriaTool().toString());
                    tool.setDescricaoTool(t.getDescricaoTool());
                    tool.setNomeTool(t.getNomeTool().toString());
                    tool.setAlugado("true");
                    tool.setUrlImgTool("img");


                    admin.setId(us.getId());
                    admin.setSenhaAdmin(us.getSenhaAdmin().toString());
                    admin.setNomeAdmin(us.getNomeAdmin().toString());
                    admin.setPagamento(t.getNomeTool().toString());
                    admin.setDataUserPay(formattedDate.toString());
                    admin.setDataNac(us.getDataNac().toString());
                    admin.setGenero(us.getGenero().toString());
                    admin.setNomeCompleto(us.getNomeCompleto().toString());
                    admin.setMorada(us.getMorada().toString());
                    admin.setTelefone(us.getTelefone());

                    dbHelper.alterarUser(admin);
                    dbHelper.close();
                    dbHelper2.alterarTools(tool);
                    dbHelper2.close();
////
//                    Intent intent = new Intent(DetalhesTools.this, SigUpUser.class);
//                    intent.putExtra("user", us);
//                    intent.putExtra("nomeFerramenta", t.getNomeTool().toString());
//                    startActivity(intent);

                    Intent intent = new Intent(DetalhesTools.this, Login.class);
                    intent.putExtra("acesso", "user");
                    startActivity(intent);
                }
            }
        });

    }
}