package com.example.toolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {
    private LinearLayout btnFerramenta, btnUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnFerramenta = findViewById(R.id.btnFerramentas);
        btnUsers = findViewById(R.id.btnUserss);

        btnFerramenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ListTools.class);
                startActivity(intent);
            }
        });

        btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, TesteList.class);
                startActivity(intent);
            }
        });
    }
}