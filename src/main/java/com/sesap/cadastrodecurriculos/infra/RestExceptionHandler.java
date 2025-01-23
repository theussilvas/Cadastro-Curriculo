package com.sesap.cadastrodecurriculos.infra;

import com.sesap.cadastrodecurriculos.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TipoArquivoErradoException.class)
    private ResponseEntity<RestErrorMessage> tipoArquivoErradoHandler(TipoArquivoErradoException exception){
        RestErrorMessage mensagemTratada = new RestErrorMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE,exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(mensagemTratada);
    }

    @ExceptionHandler(CargoNaoEncontradoException.class)
    private ResponseEntity<RestErrorMessage> cargoNaoEncontradoHandler(CargoNaoEncontradoException exception){
        RestErrorMessage mensagemTratada = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(mensagemTratada);
    }

    @ExceptionHandler(EmailNaoEncontradoException.class)
    private ResponseEntity<RestErrorMessage> emailNaoEncontradoHandler(EmailNaoEncontradoException exception){
        RestErrorMessage mensagemTratada = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(mensagemTratada);
    }

    @ExceptionHandler(EscolaridadeNaoEncontradaException.class)
    private ResponseEntity<RestErrorMessage> escolaridadeNaoEncontradoHandler(EscolaridadeNaoEncontradaException exception){
        RestErrorMessage mensagemTratada = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(mensagemTratada);
    }

    @ExceptionHandler(NomeNaoEncontradoException.class)
    private ResponseEntity<RestErrorMessage> nomeNaoEncontradoHandler(NomeNaoEncontradoException exception){
        RestErrorMessage mensagemTratada = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(mensagemTratada);
    }

    @ExceptionHandler(TamanhoInvalidoArquivoException.class)
    private ResponseEntity<RestErrorMessage> tamanhoInvalidoArquivoHanlder(TamanhoInvalidoArquivoException exception){
        RestErrorMessage mensagemTratada = new RestErrorMessage(HttpStatus.PAYLOAD_TOO_LARGE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(mensagemTratada);
    }

    @ExceptionHandler(TelefoneNaoEncontradoException.class)
    private ResponseEntity<RestErrorMessage> telefoneNaoEncontradoHandler(TelefoneNaoEncontradoException exception){
        RestErrorMessage mensagemTratada = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(mensagemTratada);
    }

    @ExceptionHandler(NumeroInvalidoException.class)
    private ResponseEntity<RestErrorMessage> numeroInvalidoHandler(NumeroInvalidoException exception){
        RestErrorMessage mensagemTratada = new RestErrorMessage(HttpStatus.LENGTH_REQUIRED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED).body(mensagemTratada);
    }

    @ExceptionHandler(NumeroRegiaoInvalidaException.class)
    private ResponseEntity<RestErrorMessage> numeroRegiaoInvalidaHandler(NumeroRegiaoInvalidaException exception){
        RestErrorMessage mensagemTratada = new RestErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(mensagemTratada);
    }

}
