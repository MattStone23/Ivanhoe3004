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
	
	@Test
	public void testLobbyClosedServer(){
		Server server = new Server(config.PORT);
		timer.wait(1);//wait for server to set up
		
		Client client1 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client1 to connect
		
		Client client2 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client2 to connect
		
		client1.sendMessage("STARTGAME");
		timer.wait(1);//wait for message to send to all clients
		
		Client client3 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client2 to connect, should fail
		assertEquals(client3.getMessage(),"REJECT|Lobby Closed");
		
		
		assertEquals(client2.getMessage(),"Game Starting");
	}
	
	@Test
	public void testLobbyFullServer(){
		Server server = new Server(config.PORT);
		timer.wait(1);//wait for server to set up
		
		System.out.println("TEST-1");
		Client client1 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client1 to connect

		System.out.println("TEST-2");
		Client client2 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client2 to connect

		System.out.println("TEST-3");
		Client client3 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client2 to connect

		System.out.println("TEST-4");
		Client client4 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client2 to connect

		System.out.println("TEST-5");
		Client client5 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client2 to connect

		System.out.println("TEST-6");
		Client client6 = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client2 to connect, should fail
		assertEquals(client6.getMessage(),"REJECT|Full Lobby");
		
		//Close everything
		
		server.shutdown();
		timer.wait(1);
		
		
	}
	
	

}
