package com.sesap.cadastrodecurriculos.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonenumber;
import com.sesap.cadastrodecurriculos.dto.CurriculoDTO;
import com.sesap.cadastrodecurriculos.entity.Curriculo;
import com.sesap.cadastrodecurriculos.entity.Escolaridade;
import com.sesap.cadastrodecurriculos.exceptions.*;
import com.sesap.cadastrodecurriculos.repository.CurriculoRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import java.io.IOException;
import java.time.LocalDateTime;



@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private EmailService emailService;

    public Curriculo processarCurriculo(CurriculoDTO curriculo, MultipartFile arquivo, String ip) throws IOException, NumberParseException {

        validarArquivo(arquivo);
        validarCampos(curriculo);
        var saveCurriculo = preencherInformacoes(curriculo, arquivo, ip);

        curriculoRepository.save(saveCurriculo);
        emailService.enviarEmail(saveCurriculo,arquivo);

        return saveCurriculo;
    }

    private void validarArquivo(MultipartFile arquivo){
        
        if(arquivo.isEmpty()){
            throw new IllegalArgumentException();
        }
        
        String nomeArquivo = arquivo.getOriginalFilename();
        if(nomeArquivo == null || !nomeArquivo.matches(".*\\.(doc|docx|pdf)$"
        )){
            throw new TipoArquivoErradoException();
        }
        
        if (arquivo.getSize() > 1_048_576){
            throw new TamanhoInvalidoArquivoException();
        }

    }

    private Curriculo preencherInformacoes(CurriculoDTO curriculo, MultipartFile arquivo, String ip) throws IOException {


        Curriculo curriculo1 = new Curriculo();
        curriculo1.setNome(curriculo.getNome());
        curriculo1.setEmail(curriculo.getEmail());
        curriculo1.setTelefone(curriculo.getTelefone());
        curriculo1.setCargoDesejado(curriculo.getCargoDesejado());
        curriculo1.setObservacoes(curriculo.getObservacoes());
        Escolaridade escolaridade = Escolaridade.valueOf(curriculo.getEscolaridade());
        curriculo1.setEscolaridade(escolaridade);

        curriculo1.setArquivoNome(arquivo.getOriginalFilename());
        curriculo1.setArquivo(arquivo.getBytes());
        curriculo1.setArquivoTipo(arquivo.getContentType());
        curriculo1.setArquivoTamanho(arquivo.getSize());
        curriculo1.setIp(ip);
        curriculo1.setDataHora(LocalDateTime.now());

        return curriculo1;
        
    }

    private void validarCampos(CurriculoDTO curriculo) throws NumberParseException {

        if (StringUtils.isBlank(curriculo.getNome())){
            throw new NomeNaoEncontradoException();
        }
        if (StringUtils.isBlank(curriculo.getEmail())){
            throw new EmailNaoEncontradoException();
        }
        if(StringUtils.isBlank(curriculo.getTelefone())){
            throw new TelefoneNaoEncontradoException();
        }
        if(StringUtils.isBlank(curriculo.getCargoDesejado())){
            throw  new CargoNaoEncontradoException();
        }
        if(StringUtils.isBlank(curriculo.getEscolaridade())){
            throw new EscolaridadeNaoEncontradaException();
        }

        if(StringUtils.isBlank(curriculo.getObservacoes())){
            curriculo.setObservacoes("Sem observações adicionadas");
        }

        if(curriculo.getTelefone().length()!= 11){
            throw new NumeroInvalidoException();
        }

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        String codigoPais = "BR";


        Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(curriculo.getTelefone(),codigoPais);
        if(!phoneNumberUtil.isValidNumberForRegion(phoneNumber,codigoPais)){
            throw new NumeroRegiaoInvalidaException();
        }

    }

}
