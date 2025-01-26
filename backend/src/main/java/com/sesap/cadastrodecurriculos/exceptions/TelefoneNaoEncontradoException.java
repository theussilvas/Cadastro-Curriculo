package com.sesap.cadastrodecurriculos.exceptions;

public class TelefoneNaoEncontradoException extends RuntimeException{

    public TelefoneNaoEncontradoException(){super("O telefone é obrigatório");}
    public TelefoneNaoEncontradoException(String message){super(message);}
}
