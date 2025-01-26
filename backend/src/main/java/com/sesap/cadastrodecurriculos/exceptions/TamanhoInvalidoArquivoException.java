package com.sesap.cadastrodecurriculos.exceptions;

public class TamanhoInvalidoArquivoException extends RuntimeException {
    public TamanhoInvalidoArquivoException(){super("O tamanho máximo para o arquivo é 1Mb");}
    public TamanhoInvalidoArquivoException(String message){super(message);}
}
