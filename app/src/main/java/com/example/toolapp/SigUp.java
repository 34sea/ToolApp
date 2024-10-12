package com.example.toolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toolapp.BdHelper.AdminDB;
import com.example.toolapp.model.Admin;

public class SigUp extends AppCompatActivity {
    EditText email, senha;
    private TextView txtLog;
    private TextView btnRe;
    Admin admin;
    AdminDB dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_up);
        admin = new Admin();
        dbHelper = new AdminDB(SigUp.this);
        txtLog = findViewById(R.id.temConta);
        btnRe = findViewById(R.id.btnEntrar);
        email = findViewById(R.id.editUsuario);
        senha = findViewById(R.id.editSenha);

        txtLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaLogin();
            }
        });

        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder msg = new AlertDialog.Builder(Registar.this);
//                msg.setMessage(email.getText().toString()+" => "+senha.getText().toString());
//                msg.show();
                admin.setSenhaAdmin(senha.getText().toString());
                admin.setNomeAdmin(email.getText().toString());
                dbHelper.salvarAdmin(admin);
                dbHelper.close();
                telaLogin();
            }
        });
    }

    public void telaLogin(){
        Intent telaLogin = new Intent(SigUp.this, Login.class);
        telaLogin.putExtra("acesso", "admin");
        startActivity(telaLogin);
    }
}