package com.javainuse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
	
	//String kafkaTopic = "java_in_use_topic";
	
	  @Autowired
	  private MessageBinding channel;

	public void send(String data) {
		final Message<String> message = MessageBuilder.withPayload(data).build();
	    
		channel.messageChannel().send(message);
	}
}