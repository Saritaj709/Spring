package com.bridgelabz.fundonotes.module.service;

import com.bridgelabz.fundonotes.module.model.MailDTO;

public interface JmsProducer {
public void sender(MailDTO dto);
}
