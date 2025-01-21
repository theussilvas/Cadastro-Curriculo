package com.sesap.cadastrodecurriculos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CurriculoResponseDTO {

    private String nome;
    private String cargoDesejado;
    @JsonFormat(pattern = "dd/MM/yyyy  HH:mm:ss")
    private LocalDateTime datahora;

    public CurriculoResponseDTO(String nome, String cargoDesejado, LocalDateTime datahora){
        this.nome = nome;
        this.cargoDesejado = cargoDesejado;
        this.datahora = datahora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargoDesejado() {
        return cargoDesejado;
    }

    public void setCargoDesejado(String cargoDesejado) {
        this.cargoDesejado = cargoDesejado;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.datahora = datahora;
    }
}
