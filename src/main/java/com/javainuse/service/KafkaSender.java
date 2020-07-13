package com.javainuse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.javainuse.service.kafkaReciever.MessageRequest;

@Service
public class KafkaSender {
	
	
	  @Autowired
	  private MessageBinding channel;

	public boolean send(MessageRequest msg) {
		final Message<MessageRequest> message = MessageBuilder.withPayload(msg).build();
		  System.out.println("==========send Main method Start===="+message.getPayload());
		  
		return channel.messageChannel().send(MessageBuilder.withPayload(msg).build());
	}
}