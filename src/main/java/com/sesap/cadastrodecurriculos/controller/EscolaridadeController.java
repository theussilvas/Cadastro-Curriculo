package com.sesap.cadastrodecurriculos.controller;

import com.sesap.cadastrodecurriculos.entity.Escolaridade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EscolaridadeController {

    @GetMapping("/escolaridades")
    public List<String> getEscolaridades(){
        return Arrays.stream(Escolaridade.values())
                .map((Escolaridade::getDescricao)).collect(Collectors.toList());
    }
}
