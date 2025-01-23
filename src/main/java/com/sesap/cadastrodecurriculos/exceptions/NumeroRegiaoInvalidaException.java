package com.sesap.cadastrodecurriculos.exceptions;

public class NumeroRegiaoInvalidaException extends RuntimeException {
    public NumeroRegiaoInvalidaException(){super("O número digitado não é válido para a região Brasil");}
    public NumeroRegiaoInvalidaException(String messagem){super(messagem);}
}
