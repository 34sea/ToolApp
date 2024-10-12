package com.example.toolapp.model;

import java.io.Serializable;

public class Admin implements Serializable {
    private long id;
    private String nomeAdmin;
    private String senhaAdmin;

    @Override
    public String toString(){
        return  "Nome: "+nomeAdmin.toString()+" Senha: "+senhaAdmin.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeAdmin() {
        return nomeAdmin;
    }

    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    public String getSenhaAdmin() {
        return senhaAdmin;
    }

    public void setSenhaAdmin(String senhaAdmin) {
        this.senhaAdmin = senhaAdmin;
    }
}
