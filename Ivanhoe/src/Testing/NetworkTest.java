package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import network.Client;
import network.Server;
import Util.config;
import Util.timer;

public class NetworkTest {

	@Test
	public void testClientServerResponse() {
		Server server = new Server(config.PORT);
		timer.wait(1);//wait for server to start up
		
		Client client = new Client(config.IP, config.PORT);
		timer.wait(1);//Wait for client to setup connection
		client.sendMessage("PING");
		timer.wait(1);//wait for message to travel across network
		
		assertEquals("Ping Received",client.getMessage());
		server.shutdown();
		timer.wait(1);
	}
	
	@Test
	public void testClientClientResponse() {
		Server server = new Server(config.PORT);
		timer.wait(1);//wait for server to set up
		
		Client client1 = new Client(config.IP, config.PORT);
		timer.wait(1);
		Client client2 = new Client(config.IP, config.PORT);
		timer.wait(2);//Wait to connect
		
		client1.sendMessage("CHAT|Hello client2");
		timer.wait(1);//wait for message to send to all clients
		
		
		assertEquals(client2.getMessage(),"Player "+client1.getPlayerNum()+"("+client1.getPlayerID()+") said: Hello client2");
		
		
		
		client2.sendMessage("CHAT|Hello to you client1");
		timer.wait(1);//wait for message to send to all clients
		
		
		assertEquals(client1.getMessage(),"Player "+client2.getPlayerNum()+"("+client2.getPlayerID()+") said: Hello to you client1");
		
		server.shutdown();
		timer.wait(1);
	}
	
	@Test
	public void testLobbyClosedServer(){
		Server server = new Server(config.PORT);
		timer.wait(1);//wait for server to set up
		
		Client client1 = new Client(config.IP, config.PORT);
		timer.wait(2);//Wait for client1 to connect
		
		Client client2 = new Client(config.IP, config.PORT);
		timer.wait(2);//Wait for client2 to connect
		
		client1.sendMessage("STARTGAME");
		timer.wait(2);//wait for message to send to all clients
		
		Client client3 = new Client(config.IP, config.PORT);
		timer.wait(2);//Wait for client2 to connect, should fail
		assertTrue(0>client3.getPlayerID());
		assertTrue(0>client3.getPlayerNum());
		
		
		assertNotEquals(client2.getGameState(),null);
		server.shutdown();
		timer.wait(1);
		
	}
	
	@Test
	public void testLobbyFullServer(){
		Server server = new Server(config.PORT);
		timer.wait(1);//wait for server to set up
		Client[] clients = new Client[6];
		
		for (int x=0;x<5;x++){
			System.out.println("Client "+(x+1)+" connecting");
			clients[x] = new Client(config.IP, config.PORT);
			timer.wait(2);//Wait for client1 to connect
			assertTrue((0 < clients[x].getPlayerID()));
			assertEquals(x+1,clients[x].getPlayerNum());
		}

		System.out.println("Client "+6+" connecting");
		clients[5] = new Client(config.IP, config.PORT);
		timer.wait(2);//Wait for client1 to connect
		assertTrue(0>clients[5].getPlayerID());
		assertTrue(0>clients[5].getPlayerNum());
		
		//Close everything
		
		server.shutdown();
		timer.wait(2);
		
		
	}
	
	

}
