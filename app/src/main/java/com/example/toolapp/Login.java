package com.example.toolapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toolapp.BdHelper.AdminDB;
import com.example.toolapp.BdHelper.UserDB;
import com.example.toolapp.model.Admin;
import com.example.toolapp.model.UserModel;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private TextView txtReg;
    private  TextView btnLog;
    EditText email, senha;

    AdminDB bdHelp;
    ArrayList<Admin> listaP2;

    UserDB bdHelp2;
    ArrayList<UserModel> listaP3;
    ArrayAdapter adapter;
    public String e, s, acesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtReg = findViewById(R.id.semConta);
        btnLog = findViewById(R.id.btnEntrar);
        email = findViewById(R.id.editUsuario);
        senha = findViewById(R.id.editSenha);

        Intent take = getIntent();

        acesso =  take.getStringExtra("acesso");

        txtReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(acesso.equals("admin")){
                    Intent telaRegistrar = new Intent(Login.this, SigUp.class);
                    startActivity(telaRegistrar);
                }else{
                    Intent telaRegistrar = new Intent(Login.this, SigUpUser.class);
                    startActivity(telaRegistrar);
                }

            }
        });

        btnLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(acesso.equals("admin")) {
                    e = email.getText().toString();
                    s = senha.getText().toString();

                    carregarAdmins(e, s);
                }else{
                    e = email.getText().toString();
                    s = senha.getText().toString();

                    carregarAdmins2(e, s);
                }

            }
        });
    }

    public void carregarAdmins(String e2, String s2) {
        bdHelp = new AdminDB(Login.this);
        listaP2 = bdHelp.getListaAdmin();
        bdHelp.close();
        int verify=0;
        if(listaP2!=null){

            for(int i=0; i<listaP2.size(); i++){
                if(e2.equals(listaP2.get(i).getNomeAdmin().toString())){
                    if(s2.equals(listaP2.get(i).getSenhaAdmin().toString())){
                        btnLog.setText("Certo");
                        Intent telaHome = new Intent(Login.this, Home.class);
                        telaHome.putExtra("perfil", listaP2.get(i));
                        startActivity(telaHome);
                        break;
                    }
                    verify=0;
                }
                verify++;
            }
            if(verify>=(listaP2.size())){
                AlertDialog.Builder msg = new AlertDialog.Builder(this);
                msg.setMessage("Dados incorretos");
                msg.show();
                verify=0;
            }

        }
    }

    public void carregarAdmins2(String e2, String s2) {
        bdHelp2 = new UserDB(Login.this);
        listaP3 = bdHelp2.getListaUser();
        bdHelp2.close();
        int verify=0;
        if(listaP3!=null){

            for(int i=0; i<listaP3.size(); i++){
                if(e2.equals(listaP3.get(i).getNomeAdmin().toString())){
                    if(s2.equals(listaP3.get(i).getSenhaAdmin().toString())){
                        btnLog.setText("Certo");
                        Intent telaHome = new Intent(Login.this, HomeUser.class);
                        telaHome.putExtra("perfil", listaP3.get(i));
                        startActivity(telaHome);
                        break;
                    }
                    verify=0;
                }
                verify++;
            }
            if(verify>=(listaP3.size())){
                AlertDialog.Builder msg = new AlertDialog.Builder(this);
                msg.setMessage("Dados incorretos");
                msg.show();
                verify=0;
            }

        }
    }
}