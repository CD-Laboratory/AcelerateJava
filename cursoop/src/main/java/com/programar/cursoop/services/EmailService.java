package com.programar.cursoop.services;

import org.springframework.mail.SimpleMailMessage;

import com.programar.cursoop.domain.Pedido;

public interface EmailService {

	void sendOderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
