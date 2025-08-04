package com.sesap.cadastrodecurriculos.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.sesap.cadastrodecurriculos.dto.CurriculoDTO;
import com.sesap.cadastrodecurriculos.dto.CurriculoResponseDTO;

import com.sesap.cadastrodecurriculos.service.CurriculoService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/curriculos")
public class CurriculoController {

    
    private final CurriculoService curriculoService;

    public CurriculoController(CurriculoService curriculoService){
        this.curriculoService = curriculoService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<CurriculoResponseDTO> submitCurriculo(@ModelAttribute CurriculoDTO curriculo, @RequestPart("arquivo") MultipartFile arquivo, HttpServletRequest request, Model model) throws IOException, NumberParseException {
            String ip = request.getRemoteAddr();
            
            var response = curriculoService.processarCurriculo(curriculo, arquivo, ip);
            CurriculoResponseDTO responseDTO = new CurriculoResponseDTO(response.getNome(), response.getCargoDesejado(), 
            response.getDataHora());
            return ResponseEntity.ok(responseDTO);
    }

}
