package network;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Util.config;
import Util.logger;
import Util.timer;

public class Client {
	
	private String ipAddress;
	private int port;
	private Socket socket;
	private boolean messageRecieved;
	private DataOutputStream streamOut;
	private ClientThread clientThread;
	private String recentMessage;
	private logger networkLog;
	
	
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
		System.out.println("CLIENT-----Establishing connection. Please wait ...");
	      try
	      {  socket = new Socket(ipAddress, port);
	         System.out.println("CLIENT-----Connected: " + socket);
	         networkLog = new logger(socket.getLocalPort()+"_clientLog");
	         start();
	      }
	      catch(UnknownHostException uhe)
	      {  System.err.println("Host unknown: " + uhe.getMessage()); }
	      catch(IOException ioe)
	      {  System.err.println("Unexpected exception: " + ioe.getMessage()); }
	}
	
	private void start() throws IOException {
	    streamOut = new DataOutputStream(socket.getOutputStream());
	    logger.println("Starting ClientThread...");
	    if (clientThread == null){
	    	clientThread = new ClientThread(this, socket);
	    }    
	}
	
	public void sendMessage(String message){
		//TODO
		try{
			networkLog.write("SENT: \t"+message);
			streamOut.writeUTF(message);
		}
		catch(IOException ioe){
			System.err.println("Unexpected exception: "+ ioe.getMessage());
		}
	}
	
	public void chat(String message){
		sendMessage("CHAT|"+message);
	}
	
	public void stop() {  
	      try { 
	    	  	if (streamOut != null) streamOut.close();

	    	  	if (socket != null) socket.close();
	    	  	
	    	  	if (networkLog != null) networkLog.close();

	    	  	this.networkLog = null;
	    	  	this.socket = null;
	    	  	this.streamOut = null;    	  
	      } catch(IOException ioe) {  
	    	  System.err.println("Error stopping client: "+ioe.getMessage());
	      }
	      clientThread.close();  
	   }
	
	public void handle(String message){
		//TODO handle
		networkLog.write("RECIEVED: \t"+message);
		if (message.equals("CLOSE")){
			clientThread.close();
		}
		recentMessage = message;
		messageRecieved = true;
	}
	
	
	
	public synchronized String getMessage(){
		while (!messageRecieved){
			//loop
		}
		messageRecieved = false;
		return recentMessage;
	}
}
