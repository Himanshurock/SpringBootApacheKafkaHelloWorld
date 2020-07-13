package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

import com.javainuse.service.kafkaReciever.MessageRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.service.KafkaSender;


@RestController
@RequestMapping(value = "/javainuse-kafka/")
public class ApacheKafkaWebController {

	@Autowired
	KafkaSender kafkaSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
        MessageRequest req = new MessageRequest("abc11123");

	 kafkaSender.send(req);

		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}

}

