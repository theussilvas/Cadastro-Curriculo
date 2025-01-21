package com.sesap.cadastrodecurriculos.entity;

public enum Escolaridade {
    Fundamental ("Ensino Fundamental"),
    FundamentalInc("Ensino Fundamenta incompleto"),
    Medio("Ensino Médio"),
    MedioInc("Ensino Médio Incompleto"),
    Superior("Ensino Superior Completo");


    private final String descricao;

    Escolaridade(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}

