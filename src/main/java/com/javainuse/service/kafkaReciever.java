package com.javainuse.service;

import java.util.concurrent.CountDownLatch;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	 public static class MessageRequest {
	        private String id;

	        @JsonCreator
	        public MessageRequest(@JsonProperty("id") String id) {
	            this.id = id;
	        }

	        public String getId() {
	            return this.id;
	        }
	    }
	 
}
