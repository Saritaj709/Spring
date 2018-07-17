package com.bridgelabz.fundonotes.module.service;

import com.bridgelabz.fundonotes.module.model.MailDTO;

public interface JmsConsumer {
public void receiver(MailDTO dto);
}
