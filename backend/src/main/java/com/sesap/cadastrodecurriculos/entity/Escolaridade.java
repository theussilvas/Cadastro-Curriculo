package com.sesap.cadastrodecurriculos.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Escolaridade {
    FUNDAMENTAL_COMPLETO ("Ensino Fundamental"),
    FUNDAMENTAL_INCOMPLETO("Ensino Fundamenta incompleto"),
    MEDIO("Ensino Médio"),
    MEDIO_INCOMPLETO("Ensino Médio Incompleto"),
    SUPERIOR("Ensino Superior Completo");


    private final String descricao;

    Escolaridade(String descricao){
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao(){
        return descricao;
    }

    @JsonCreator
    public static Escolaridade fromValue(String value){
        for(Escolaridade escolaridade1:values()){
            String atualEscolaridade = escolaridade1.getDescricao();
            if (atualEscolaridade.equals(value)) {
                return escolaridade1;
            }
        }
        throw new IllegalArgumentException("Valor inválido");       
    }

}

