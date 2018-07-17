package com.bridgelabz.fundonotes.module.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.module.model.MailDTO;
import com.bridgelabz.fundonotes.module.service.JmsProducer;

@Service
public class RabbitMQSender implements JmsProducer{
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${bridgelabz.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${bridgelabz.rabbitmq.routingkey}")
	private String routingkey;	
	@Override
	public void sender(MailDTO company) {
		rabbitTemplate.convertAndSend(exchange, routingkey, company);
		System.out.println("Send msg = " + company);
		//return company;
	    
	}
}