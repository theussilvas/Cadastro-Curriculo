package com.sesap.cadastrodecurriculos.service;

import com.sesap.cadastrodecurriculos.dto.CurriculoResponseDTO;
import com.sesap.cadastrodecurriculos.entity.Curriculo;
import com.sesap.cadastrodecurriculos.repository.CurriculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private EmailService emailService;

    public void processarCurriculo(Curriculo curriculo, MultipartFile arquivo, String ip) throws IOException {

        validarArquivo(arquivo);

        preencherInformacoes(curriculo, arquivo, ip);

        curriculoRepository.save(curriculo);
        emailService.enviarEmail(curriculo,arquivo);
    }

    private void validarArquivo(MultipartFile arquivo){
        if(arquivo.isEmpty()){
            throw new IllegalArgumentException();
        }

        String nomeArquivo = arquivo.getOriginalFilename();
        if(nomeArquivo == null || !nomeArquivo.matches(".*\\.(doc | docx | pdf)$"
        )){
            throw new IllegalArgumentException("Arquivo invalido, somente os formatos .doc, .docx e .pdf são aceitos");
        }

        if (arquivo.getSize() > 1_048_576){
            throw new IllegalArgumentException("Tamanho do arquivo não pode exceder 1mb");
        }

    }

    private void preencherInformacoes(Curriculo curriculo, MultipartFile arquivo, String ip) throws IOException {

        byte[] arquivoBytes = arquivo.getBytes();

        curriculo.setArquivoNome(arquivo.getOriginalFilename());
        curriculo.setArquivoTipo(arquivo.getContentType());
        curriculo.setArquivoTamanho(arquivo.getSize());
        curriculo.setIp(ip);
        curriculo.setDataHora(LocalDateTime.now());
    }

}
