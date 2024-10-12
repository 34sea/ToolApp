package com.example.toolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.toolapp.model.UserModel;

public class HomeUser extends AppCompatActivity {
    UserModel user;
    private TextView userName, statusUser, txtNu, txtMorad, txtSex, txtDataNa;
    private LinearLayout btnLogout, aluguerUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        statusUser = findViewById(R.id.txtPagamento);
        userName = findViewById(R.id.txtNomeUser2);
        txtNu = findViewById(R.id.txtNum);
        txtMorad = findViewById(R.id.txtMorada);
        txtSex = findViewById(R.id.txtSexo);
        txtDataNa = findViewById(R.id.txtDataNac);

        aluguerUser = findViewById(R.id.txtAluguer);
        btnLogout = findViewById(R.id.btnLogoutL);
        Intent dados = getIntent();
        user = (UserModel) dados.getSerializableExtra("perfil");
        if(user!=null){
            userName.setText(user.getNomeCompleto());
            txtNu.setText(String.valueOf(user.getTelefone()));
            txtMorad.setText(user.getMorada().toString());
            txtSex.setText(user.getGenero().toString());
            txtDataNa.setText(user.getDataNac().toString());
            statusUser.setText(user.getPagamento()!="false"?user.getPagamento().toString():"none");
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeUser.this, MainActivity.class);
                startActivity(intent);
            }
        });

        aluguerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getPagamento().equals("false")){
                    Intent intent = new Intent(HomeUser.this, Loja.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(HomeUser.this, Alugar.class);
                    intent.putExtra("user", user);
                    startActivity(intent);

                }
            }
        });

    }
}