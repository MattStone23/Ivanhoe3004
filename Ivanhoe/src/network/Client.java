package network;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Util.config;

public class Client {
	
	private String ipAddress;
	private int port;
	private Socket socket;
	private boolean messageRecieved;
	private DataOutputStream streamOut;
	private ClientThread clientThread;
	private String recentMessage;
	
	
	public Client(){
		port = config.PORT;
		ipAddress = config.IP;
		recentMessage = new String();
		
		connectToServer();
	}
	
	public Client(String ipAdd, int portNum){
		port = portNum;
		ipAddress = ipAdd;
		recentMessage = new String();
		
		connectToServer();
	}
	
	private void connectToServer(){
		System.out.println("Establishing connection. Please wait ...");
	      try
	      {  socket = new Socket(ipAddress, port);
	         System.out.println("Connected: " + socket);
	         start();
	      }
	      catch(UnknownHostException uhe)
	      {  System.out.println("Host unknown: " + uhe.getMessage()); }
	      catch(IOException ioe)
	      {  System.out.println("Unexpected exception: " + ioe.getMessage()); }
	}
	
	private void start() throws IOException {
	    streamOut = new DataOutputStream(socket.getOutputStream());
	    System.out.println("Starting ClientThread...");
	    if (clientThread == null){
	    	clientThread = new ClientThread(this, socket);
	    }
	}
	
	public void sendMessage(String message){
		//TODO
		try{
			streamOut.writeUTF(message);
		}
		catch(IOException ioe){
			System.out.println("Unexpected exception: "+ ioe.getMessage());
		}
	}
	
	public void stop() {  
	      try { 
	    	  	if (streamOut != null) streamOut.close();

	    	  	if (socket != null) socket.close();

	    	  	this.socket = null;
	    	  	this.streamOut = null;    	  
	      } catch(IOException ioe) {  
	    	  System.out.println("Error stopping client: "+ioe.getMessage());
	      }
	      clientThread.close();  
	   }
	
	public void handle(String message){
		//TODO handle
		if (message.equals("CLOSE")){
			clientThread.close();
		}
		recentMessage = message;
		messageRecieved = true;
	}
	
	
	public boolean messageRecieved(){
		return messageRecieved;
	}
	public String getMessage(){
		messageRecieved = false;
		return recentMessage;
	}
}
