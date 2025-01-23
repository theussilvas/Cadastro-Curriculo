package com.sesap.cadastrodecurriculos.exceptions;

public class EmailNaoEncontradoException extends RuntimeException {
    public EmailNaoEncontradoException(){super("O email é obrigatório");}
    public EmailNaoEncontradoException(String message){super(message);}
}
