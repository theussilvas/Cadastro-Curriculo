package com.sesap.cadastrodecurriculos.controller;

import com.sesap.cadastrodecurriculos.dto.CurriculoResponseDTO;
import com.sesap.cadastrodecurriculos.entity.Curriculo;
import com.sesap.cadastrodecurriculos.entity.Escolaridade;
import com.sesap.cadastrodecurriculos.service.CurriculoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/curriculos")
public class CurriculoController {

    @Autowired
    private CurriculoService curriculoService;

    @PostMapping("/enviar")
    public ResponseEntity<CurriculoResponseDTO> submitCurriculo(@RequestParam @NotEmpty String nome, @RequestParam @NotEmpty String email, @RequestParam @NotEmpty String telefone, @RequestParam @NotEmpty String cargoDesejado, @RequestParam @NotEmpty String escolaridade, @RequestParam(required = false) String observacoes, @RequestParam MultipartFile arquivo, HttpServletRequest request, Model model) {
        
        try {
            Curriculo curriculo = new Curriculo();
            curriculo.setNome(nome);
            curriculo.setEmail(email);
            curriculo.setTelefone(telefone);
            curriculo.setCargoDesejado(cargoDesejado);
            curriculo.setEscolaridade(Escolaridade.valueOf(escolaridade));
            if (observacoes == null) {
                curriculo.setObservacoes("Nenhuma observacao adicionada");
            } else {
                curriculo.setObservacoes(observacoes);
            }
            String ip = request.getRemoteAddr();
            
            curriculoService.processarCurriculo(curriculo, arquivo, ip);
            CurriculoResponseDTO responseDTO = new CurriculoResponseDTO(curriculo.getNome(), curriculo.getCargoDesejado(), curriculo.getDataHora());
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            
            return ResponseEntity.badRequest().body(null);
        }
    }

}
