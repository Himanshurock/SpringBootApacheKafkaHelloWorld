package com.javainuse.service;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class kafkaReciever {
	
	
	 @StreamListener("InputCommonChannelTest")
	public void reciever(@Payload String msg) {
		 
		 System.out.println("============msg======="+msg);
	}
	 
	 
}
