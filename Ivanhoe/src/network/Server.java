package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

import Util.config;

public class Server implements Runnable {
	
	private HashMap<Integer, ServerThread> clients = null;
	private ServerSocket server = null;
	private Thread       thread = null;
	private int clientCount = 0;
	private int port;
	private int maxClients;
	
	public Server(){
		port = config.PORT;
		clients = new HashMap<Integer, ServerThread>();
		maxClients = config.MAX_CLIENTS;
		
		bindToPort();
	}
	public Server(int portNum){
		port = portNum;
		clients = new HashMap<Integer, ServerThread>();
		maxClients = config.MAX_CLIENTS;
		
		bindToPort();
	}
	
	private void bindToPort(){
		try{
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);  
			server.setReuseAddress(true);
			System.out.println("Server started: " + server);
			start(); 
		}
		catch(IOException ioe){
			System.out.println("Can not bind to port " + port + ": " + ioe.getMessage()); 
		}
	}
	public void start(){
		if (thread == null){
			thread = new Thread(this); 
			thread.start();
			System.out.println("Server started: "+ server +": "+thread.getId());
		}
	}
	
	public void run(){
		while (thread != null){
			try{
				System.out.println("Waiting for a client ..."); 
				addThread(server.accept());
			}
			catch(IOException ioe){
					System.out.println("Server accept error: " + ioe);
			}
		}
	}
	
	private void addThread(Socket socket){
		if (clientCount < maxClients){
			System.out.println("Accepting Client: " + socket);
			try	{
				ServerThread serverThread = new ServerThread(this, socket);
				serverThread.open(); 
				serverThread.start();  
				clients.put(serverThread.getID(), serverThread);
				clientCount++; 
			}
			catch(IOException ioe){
				System.out.println("Error opening thread: " + ioe); 
			} 
		}
		else
			System.out.println("Client refused: maximum " + maxClients + " reached.");
	}
	public synchronized void removeThread(int ID){
		if (clients.containsKey(ID)){
			ServerThread toTerm = clients.get(ID);
			System.out.println("Removing client thread " + ID);
			clients.remove(ID);
			clientCount--;
			
			toTerm.close();
			toTerm = null;
			
		}
	}
	public synchronized void handle(int clientNum, String message){
		//TODO handle stuff
		//if quit message
		
		//if shutdown message
		
		ServerThread from = clients.get(clientNum);
		for (ServerThread to : clients.values()){
			if (from.getID() != to.getID()){
				to.send(message);
			}
			else{
				to.send("Hello Client From Server");
			}
		}
	}
	
	/** Shutdown the server cleanly */
	public void shutdown() {
		Set<Integer> keys = clients.keySet();

		if (thread != null) {
			thread = null;
		}

		try {
			for (Integer key : keys) {
				//clients.get(key).send("CLOSE");
				clients.get(key).close();
				clients.put(key, null);
			}
			clients.clear();
			if (server!=null){server.close();}
		} catch (IOException e) {
			System.out.println("Unexpected Error while shutting down: "+e.getMessage());
		}
		System.out.println("Server Shutdown cleanly "+ server);
	}
}
