package com.javainuse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
	
	
	  @Autowired
	  private MessageBinding channel;

	public boolean send(String data) {
		final Message<String> message = MessageBuilder.withPayload(data).build();
	    
		return channel.messageChannel().send(message);
	}
}
