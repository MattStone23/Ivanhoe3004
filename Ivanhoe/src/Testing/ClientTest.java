package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import network.Client;
import network.Server;
import Util.config;

public class ClientTest {

	@Test
	public void testClientServerResponse() {
		Server server = new Server(config.PORT);
		server.start();
		Client client = new Client(config.IP, config.PORT);
		client.sendMessage("Hello server");
		while (!client.messageRecieved()){
			
		}
		assertEquals(client.getMessage(),"Hello Client");
	}

}
