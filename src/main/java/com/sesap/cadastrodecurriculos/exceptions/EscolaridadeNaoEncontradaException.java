package com.sesap.cadastrodecurriculos.exceptions;

public class EscolaridadeNaoEncontradaException extends RuntimeException {
    public EscolaridadeNaoEncontradaException(){super("Escolaridade inválida");}
    public EscolaridadeNaoEncontradaException(String message){super(message);}
}
