package com.example.toolapp.model;

import java.io.Serializable;

public class Tool implements Serializable {
    private long id;
    private String nomeTool;
    private String descricaoTool;
    private String categoriaTool;
    private String dataTool;
    private String urlImgTool;
    private int precoTool;
    private int quantidadeTool;
    private String alugado;

    @Override
    public String toString(){
        return  nomeTool.toString()+": "+String.valueOf(precoTool)+",00mt";
    }

    public String getAlugado() {
        return alugado;
    }

    public void setAlugado(String alugado) {
        this.alugado = alugado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrecoTool() {
        return precoTool;
    }

    public void setPrecoTool(int precoTool) {
        this.precoTool = precoTool;
    }

    public String getDescricaoTool() {
        return descricaoTool;
    }

    public void setDescricaoTool(String descricaoTool) {
        this.descricaoTool = descricaoTool;
    }

    public String getCategoriaTool() {
        return categoriaTool;
    }

    public void setCategoriaTool(String categoriaTool) {
        this.categoriaTool = categoriaTool;
    }

    public String getDataTool() {
        return dataTool;
    }

    public void setDataTool(String dataTool) {
        this.dataTool = dataTool;
    }

    public String getUrlImgTool() {
        return urlImgTool;
    }

    public void setUrlImgTool(String urlImgTool) {
        this.urlImgTool = urlImgTool;
    }

    public String getNomeTool() {
        return nomeTool;
    }

    public void setNomeTool(String nomeTool) {
        this.nomeTool = nomeTool;
    }

    public int getQuantidadeTool() {
        return quantidadeTool;
    }

    public void setQuantidadeTool(int quantidadeTool) {
        this.quantidadeTool = quantidadeTool;
    }
}
