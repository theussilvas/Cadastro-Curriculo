package com.sesap.cadastrodecurriculos.service;

import com.sesap.cadastrodecurriculos.entity.Curriculo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(Curriculo curriculo, MultipartFile arquivo){

        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(curriculo.getEmail());
            helper.setSubject("Novo cadastro de curriculo");
            helper.setText(
                    "Nome: " + curriculo.getNome() + "\n" +
                    "Email: " + curriculo.getEmail() + "\n" +
                    "Telefone: " + curriculo.getTelefone() + "\n" +
                    "Cargo desejado " + curriculo.getCargoDesejado() + "\n" +
                    "Escolaridade: " + curriculo.getEscolaridade() + "\n" +
                    "Observações: " + curriculo.getObservacoes()
            );
            helper.addAttachment(arquivo.getOriginalFilename(), arquivo);
            javaMailSender.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
