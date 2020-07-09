package com.javainuse.service;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


/**
 * Binding definition for Message message channel.
 */
public interface MessageBinding {

  /**
   * Messages.
   *
   * @return a channel which defines the exchange and queue for consuming messages
   */
  @Input("InputCommonChannelTest")
  SubscribableChannel messagesSubscribableChannel();

  /**
   * MessageApiEvent.
   *
   * @return channel which defines the exchange and queue for publishing messages for downstream
   */
  @Output("CommonChannelTest")
  MessageChannel messageChannel();
}