package com.sesap.cadastrodecurriculos.exceptions;

public class TipoArquivoErradoException extends  RuntimeException{

    public TipoArquivoErradoException(){super("Extensão errada, somente são aceitos .pdf, .doc e .docx");}
    public TipoArquivoErradoException(String message){super(message);}
}
