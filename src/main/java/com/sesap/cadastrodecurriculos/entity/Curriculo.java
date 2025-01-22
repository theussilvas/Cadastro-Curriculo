package com.sesap.cadastrodecurriculos.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cargoDesejado;
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;

    private String arquivoNome;
    private String arquivoTipo;


    @Lob
    private byte[] arquivo;

    private Long arquivoTamanho;

    private String ip;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataHora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargoDesejado() {
        return cargoDesejado;
    }

    public void setCargoDesejado(String cargoDesejado) {
        this.cargoDesejado = cargoDesejado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getArquivoNome() {
        return arquivoNome;
    }

    public void setArquivoNome(String arquivoNome) {
        this.arquivoNome = arquivoNome;
    }

    public String getArquivoTipo() {
        return arquivoTipo;
    }

    public void setArquivoTipo(String arquivoTipo) {
        this.arquivoTipo = arquivoTipo;
    }

    public Long getArquivoTamanho() {
        return arquivoTamanho;
    }

    public void setArquivoTamanho(Long arquivoTamanho) {
        this.arquivoTamanho = arquivoTamanho;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }
}
