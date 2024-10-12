package com.example.toolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.toolapp.BdHelper.AdminDB;
import com.example.toolapp.BdHelper.UserDB;
import com.example.toolapp.model.Admin;
import com.example.toolapp.model.UserModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class SigUpUser extends AppCompatActivity {
    EditText email, senha, editUsuarioComplet, editTexContat, editTextMorad;
    private TextView txtLog;
    private TextView btnRe, txtTesteIte;
    UserModel admin, usero;
    UserDB dbHelper;
    String nomeFerrament;
    private String selectedItem;
    private Spinner spinner;
    private DatePicker datePicker;
    private int year, month, day;
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_up_user);
        admin = new UserModel();
        dbHelper = new UserDB(SigUpUser.this);
        txtLog = findViewById(R.id.temConta);
        btnRe = findViewById(R.id.btnEntrar);
        email = findViewById(R.id.editUsuario);
        senha = findViewById(R.id.editSenha);
        editUsuarioComplet = findViewById(R.id.editUsuarioCompleto);
        editTexContat = findViewById(R.id.editTexContato);
        editTextMorad = findViewById(R.id.editTextMorada);
        spinner = findViewById(R.id.spnSexo);
        txtTesteIte = findViewById(R.id.txtTesteItem);


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);

        txtTesteIte.setText(formattedDate.toString());

        Intent take = getIntent();
        usero = (UserModel) take.getSerializableExtra("user");
        nomeFerrament = take.getStringExtra("nomeFerramenta");


        String[] items = {"Sexo", "Masculino", "Feminino"};


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


        datePicker = findViewById(R.id.datePicker);


        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                year = year;
                month = monthOfYear;
                day = dayOfMonth;

                // Exemplo: imprime a data selecionada
                selectedDate = day + "/" + (month + 1) + "/" + year;
//
            }
        });




        txtLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaLogin();
            }
        });

        if(usero!=null){
            btnRe.setText("Terminar");
            senha.setText(usero.getSenhaAdmin());
            email.setText(usero.getNomeAdmin());
        }

        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTesteIte.setText(selectedDate.toString());
//                AlertDialog.Builder msg = new AlertDialog.Builder(Registar.this);
//                msg.setMessage(email.getText().toString()+" => "+senha.getText().toString());
//                msg.show();
                admin.setSenhaAdmin(senha.getText().toString());
                admin.setNomeAdmin(email.getText().toString());
                admin.setPagamento("false");
                admin.setDataUserPay(formattedDate.toString());
                admin.setTelefone(Integer.parseInt(editTexContat.getText().toString()));
                admin.setGenero(selectedItem.toString());
                admin.setMorada(editTextMorad.getText().toString());
                admin.setDataNac(selectedDate.toString());
                admin.setNomeCompleto(editUsuarioComplet.getText().toString());
                if(usero!=null){
                    admin.setPagamento(nomeFerrament.toString());
                    dbHelper.alterarUser(admin);
                    dbHelper.close();
                }else{
                    dbHelper.salvarUser(admin);
                    dbHelper.close();
                }


                telaLogin();

            }
        });
    }

    public void telaLogin(){
        Intent telaLogin = new Intent(SigUpUser.this, Login.class);
        telaLogin.putExtra("acesso", "user");
        startActivity(telaLogin);
    }
}