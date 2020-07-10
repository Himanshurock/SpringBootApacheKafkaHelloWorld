import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javainuse.SpringBootHelloWorldApplication;
import com.javainuse.service.MessageBinding;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringBootHelloWorldApplication.class)
@EnableAutoConfiguration
public class KafkaSenderIntegerationIT {
	
	  @Autowired
	  private MessageBinding channel;

	  @Test
	  public void send() {
		  String data = "IntegrationTest2";
		final Message<String> message = MessageBuilder.withPayload(data).build();
	    
		Assert.assertTrue(channel.messageChannel().send(message));
	}
}

