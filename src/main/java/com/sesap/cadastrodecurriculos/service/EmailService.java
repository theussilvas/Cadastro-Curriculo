package com.sesap.cadastrodecurriculos.service;

import com.sesap.cadastrodecurriculos.entity.Curriculo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(Curriculo curriculo, MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            logger.error("O arquivo do currículo está ausente ou vazio.");
            throw new IllegalArgumentException("Arquivo do currículo é obrigatório.");
        }
        

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            
            helper.setTo(curriculo.getEmail());
            helper.setSubject("Novo cadastro de currículo");
            helper.setText(gerarCorpoEmail(curriculo));
            helper.addAttachment(arquivo.getOriginalFilename(), arquivo);
            
            javaMailSender.send(message);
            System.out.println("Passei");
            logger.info("E-mail enviado com sucesso para: {}", curriculo.getEmail());
            System.out.println(curriculo.getDataHora());

        } catch (MessagingException e) {
            logger.error("Erro ao enviar e-mail para {}: {}", curriculo.getEmail(), e.getMessage());
            throw new RuntimeException("Erro ao enviar o e-mail", e);
        }
    }

    private String gerarCorpoEmail(Curriculo curriculo) {
        return String.format(
                "Nome: %s\n" +
                "Email: %s\n" +
                "Telefone: %s\n" +
                "Cargo desejado: %s\n" +
                "Escolaridade: %s\n" +
                "Observações: %s\n" +
                "Ip:  %s \n" +
                "Data hora:  %s ",
                curriculo.getNome(),
                curriculo.getEmail(),
                curriculo.getTelefone(),
                curriculo.getCargoDesejado(),
                curriculo.getEscolaridade(),
                curriculo.getObservacoes() != null ? curriculo.getObservacoes() : "Nenhuma observação adicionada",
                curriculo.getIp(),
                curriculo.getDataHora()
        );
    }
}
