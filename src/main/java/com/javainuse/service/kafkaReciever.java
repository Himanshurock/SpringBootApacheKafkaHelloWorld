package com.javainuse.service;

import java.util.concurrent.CountDownLatch;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class kafkaReciever {
	
	 private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	 @StreamListener("InputCommonChannelTest")
	public void reciever(MessageRequest msg) {
		 
		 System.out.println("============msg======="+msg.getId());
		 latch.countDown();
	}
}
