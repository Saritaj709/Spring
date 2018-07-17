package com.bridgelabz.fundonotes.module.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundonotes.module.mail.MailServiceImpl;
import com.bridgelabz.fundonotes.module.model.MailDTO;
import com.bridgelabz.fundonotes.module.service.JmsConsumer;

@Component
public class RabbitMQReceiver implements JmsConsumer{
	
	@Autowired
	private MailServiceImpl emailSender;
	@Override
	@RabbitListener(queues = "${bridgelabz.rabbitmq.queue}")
	public void receiver(MailDTO msg) {
		System.out.println("Received message : " + msg);
		//return msg;
		emailSender.sendMail(msg);
	}
}
