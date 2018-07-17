package com.bridgelabz.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.bridgelabz.rabbitmq.model.Employee;

@Component
public class RabbitMQReceiver {
	
	@RabbitListener(queues = "${bridgelabz.rabbitmq.queue}")
	public void receivedMsg(Employee msg) {
		System.out.println("Received message : " + msg);
	}
}
