package com.example.user.producers;

import com.example.user.DTOs.EmailDTO;
import com.example.user.domain.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setUserID(user.getUserId());
        emailDTO.setEmailTo(user.getEmail());
        emailDTO.setSubject("Cadastro realizado com sucesso");
        emailDTO.setText(user.getName() + "Seja bem vindo(a)! \n Agradecemos o seu cadastro");

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }
}
