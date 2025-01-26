package com.sesap.cadastrodecurriculos.controller;


import com.sesap.cadastrodecurriculos.entity.Escolaridade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class EscolaridadeController {

    @GetMapping("/escolaridades")
    public List<String> getEscolaridades(){
        return Arrays.stream(Escolaridade.values())
                .map(Enum::name)
                .toList();
    }
}
