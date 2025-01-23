package com.sesap.cadastrodecurriculos.exceptions;

public class NumeroInvalidoException extends RuntimeException{

    public NumeroInvalidoException(){super("O número de telefone deve conter 11 dígitos");}
    public NumeroInvalidoException(String message){super(message);}
}
