package com.example.toolapp.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private long id;
    private String nomeAdmin;
    private String senhaAdmin;
    private String pagamento;
    private String dataUserPay;
    private String dataNac;
    private String nomeCompleto;
    private String genero;
    private String morada;
    private int telefone;

    @Override
    public String toString(){
        return  "Nome: "+nomeAdmin.toString();
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getDataNac() {
        return dataNac;
    }

    public void setDataNac(String dataNac) {
        this.dataNac = dataNac;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getDataUserPay() {
        return dataUserPay;
    }

    public void setDataUserPay(String dataUserPay) {
        this.dataUserPay = dataUserPay;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
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
