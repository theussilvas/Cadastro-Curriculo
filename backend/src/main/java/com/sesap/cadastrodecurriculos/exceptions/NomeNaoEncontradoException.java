package com.sesap.cadastrodecurriculos.exceptions;

public class NomeNaoEncontradoException extends RuntimeException{

    public NomeNaoEncontradoException(){super("O nome é obrigatório");}

    public NomeNaoEncontradoException(String message){super(message);}
}
