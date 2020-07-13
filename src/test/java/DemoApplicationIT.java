
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javainuse.SpringBootHelloWorldApplication;
import com.javainuse.service.KafkaSender;
import com.javainuse.service.MessageBinding;
import com.javainuse.service.kafkaReciever;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import static org.mockito.ArgumentMatchers.argThat;


@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@SpringBootTest(classes = SpringBootHelloWorldApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = SpringBootHelloWorldApplication.class)
@EnableAutoConfiguration
@EnableBinding(MessageBinding.class)
@TestPropertySource(
        properties = {
        		"spring.cloud.stream.bindings.InputCommonChannelTest.destination=MyTopic",
        		 "spring.cloud.stream.bindings.CommonChannelTest.destination=MyTopic",
        		 "spring.kafka.bootstrap-servers=kafka:9092"
        })
public class DemoApplicationIT {
	
	@TestConfiguration
    static class Config {

        @Bean
        public BeanPostProcessor messageRequestListenerPostProcessor() {
            return new ProxiedMockPostProcessor(kafkaReciever.class);
        }
        
        static class ProxiedMockPostProcessor implements BeanPostProcessor {
            private final Class<?> mockedClass;

            public ProxiedMockPostProcessor(Class<?> mockedClass) {
                this.mockedClass = mockedClass;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName)
                    throws BeansException {
                if (mockedClass.isInstance(bean)) {
                    return Mockito.mock(mockedClass, AdditionalAnswers.delegatesTo(bean));
                }
                return bean;
            }
        }
	}
	
	   @Autowired
	    private kafkaReciever listener;

	    @Autowired
	    private KafkaSender producer;
	    
	    @Autowired
		  private MessageBinding channel;
	    
	    @Test
	    public void messageIsReceived() {
	        MessageRequest req = new MessageRequest("abc123");
	        System.out.println("Before==========");
	        channel.messageChannel().send(MessageBuilder.withPayload(req).build());
	        System.out.println("After==========");

	        // the message actually gets received. Need to do a timeout because I cannot manually force
	        // a consumption of this message from Kafka. The default for timeout() is to check every
	        // 10ms up to the timeout
	        verify(listener, timeout(5000)).reciever(argThat(m -> m.getId().equals(req.getId())));
	       // reciever(argThat(m -> m..equals(req.getId())));

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
