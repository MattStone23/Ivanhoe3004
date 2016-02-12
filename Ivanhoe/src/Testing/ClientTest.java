package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import network.Client;
import network.Server;
import Util.config;
import Util.timer;

public class ClientTest {

	@Test
	public void testClientServerResponse() {
		Server server = new Server(config.PORT);
		timer.wait(1);//wait for server to start up
		
		Client client = new Client(config.IP, config.PORT);
		client.sendMessage("Hello server");
		
		assertEquals(client.getMessage(),"Hello Client From Server");
		server.shutdown();
		timer.wait(1);
	}
	
	@Test
	public void testClientClientResponse() {
		Server server = new Server(config.PORT);
		timer.wait(1);//wait for server to set up
		
		Client client1 = new Client(config.IP, config.PORT);
		Client client2 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait to connect
		
		client1.sendMessage("Hello client2");
		timer.wait(1);//wait for message to send to all clients
		
		
		assertEquals(client2.getMessage(),"Hello client2");
		
		
		
		client2.sendMessage("Hello to you client1");
		timer.wait(1);//wait for message to send to all clients
		
		
		assertEquals(client1.getMessage(),"Hello to you client1");
		
		server.shutdown();
		timer.wait(1);
	}
	
	

}
