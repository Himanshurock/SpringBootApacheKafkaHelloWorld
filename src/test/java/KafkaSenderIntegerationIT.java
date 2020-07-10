import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javainuse.SpringBootHelloWorldApplication;
import com.javainuse.service.KafkaSender;
import com.javainuse.service.MessageBinding;
import com.javainuse.service.kafkaReciever;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@SpringBootTest(classes = SpringBootHelloWorldApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = SpringBootHelloWorldApplication.class)
@EnableAutoConfiguration
@EnableBinding(MessageBinding.class)
public class KafkaSenderIntegerationIT {
	
	  @Autowired
	  private KafkaSender sender;
	  
	  @Autowired
	  kafkaReciever kafkaReciever;
	  
	/*
	 * @Autowired MessageBinding messageBinding;
	 */
	
	/*
	 * @Autowired private MessageCollector collector;
	 */
	 
	
	  @Test 
	  public void send() throws InterruptedException { 
		  String data = "IntegrationTest332274";
		  System.out.println("==========send test method====");
		  boolean b = sender.send(data); 
		  System.out.println("==========sender.send(data)===="+b);
		  System.out.println("==========1===="+System.currentTimeMillis());
		/*
		 * kafkaReciever.getLatch().await(15000, TimeUnit.MILLISECONDS);
		 * System.out.println("==========2===="+System.currentTimeMillis());
		 * Thread.sleep(15000);
		 * System.out.println("==========3===="+System.currentTimeMillis());
		 * assertThat(kafkaReciever.getLatch().getCount()).isEqualTo(0)
		 */;
		  System.out.println("==========Before End====");
		  Assert.assertTrue(b);
		  System.out.println("==========End====");
	  }
	 
	  @Test 
	  public void send2() throws InterruptedException { 
		  kafkaReciever.getLatch().await(15000, TimeUnit.MILLISECONDS);
		  assertThat(kafkaReciever.getLatch().getCount()).isEqualTo(0);

	  }
	  
	/*
	 * @Test public void send2() { String data = "IntegrationTest444";
	 * 
	 * final Message<String> message = MessageBuilder.withPayload(data).build();
	 * Assert.assertTrue(messageBinding.messageChannel().send(message)); //
	 * System.out.println("===="+messageBinding.messageChannel().send(message));
	 * 
	 * // BlockingQueue<Message<?>> messages =
	 * collector.forChannel(messageBinding.messageChannel());
	 * //Assert.assertEquals(1, messages.size());
	 * 
	 * }
	 */
}

