package com.example.toolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.toolapp.BdHelper.UserDB;
import com.example.toolapp.model.Tool;
import com.example.toolapp.model.UserModel;

public class Alugar extends AppCompatActivity {
    UserModel admin;
    UserDB dbHelper;
    private UserModel us;
    private TextView btnPa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alugar);

        btnPa = findViewById(R.id.btnPay);
        admin = new UserModel();
        dbHelper = new UserDB(Alugar.this);
        Intent take = getIntent();
        us = (UserModel) take.getSerializableExtra("user");

        btnPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin.setId(us.getId());
                admin.setSenhaAdmin(us.getSenhaAdmin().toString());
                admin.setNomeAdmin(us.getNomeAdmin().toString());
                admin.setPagamento("false");
                admin.setDataUserPay(us.getDataUserPay().toString());
                admin.setDataNac(us.getDataNac().toString());
                admin.setGenero(us.getGenero().toString());
                admin.setNomeCompleto(us.getNomeCompleto().toString());
                admin.setMorada(us.getMorada().toString());
                admin.setTelefone(us.getTelefone());

                dbHelper.alterarUser(admin);
                dbHelper.close();

                Intent intent = new Intent(Alugar.this, Login.class);
                intent.putExtra("acesso", "user");
                startActivity(intent);
            }
        });
    }
}