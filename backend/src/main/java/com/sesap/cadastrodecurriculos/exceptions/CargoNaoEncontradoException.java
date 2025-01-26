package com.sesap.cadastrodecurriculos.exceptions;

public class CargoNaoEncontradoException extends RuntimeException {
    public CargoNaoEncontradoException(){super("O cargo desejado é obrigatório");}
    public CargoNaoEncontradoException(String message){super(message);}
}
