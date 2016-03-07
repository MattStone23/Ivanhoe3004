package network;
import java.io.*;
import java.net.Socket;

import Util.logger;


public class ServerThread extends Thread {
	private Server       server    = null;
	private Socket           socket    = null;
	private int              ID        = -1;
	private DataInputStream  inputStream  =  null;
	private DataOutputStream outputStream = null;
	private boolean running;
	private logger netLog;
	
	// username of corresponding client
//	private String username;
//	public String getUsername() { return username; }
	
	public ServerThread(Server _server, Socket _socket){
		super();
		server = _server;
		socket = _socket;
		ID     = socket.getPort();
		
	}
	
	public int getID() {
		return ID;
	}
	
	public String getSocketAddress(){
		return socket.getLocalAddress().getHostAddress();
	}
	
	public void run() {
		System.out.println("Server Thread " + ID + " running.");
		String received;
		running = true;
		while (running) {
			try {
				
				// serverThread does the 'receiving' from client, and then tells server what to do
				received = inputStream.readUTF();
				netLog.write("RECIEVED: \t"+received);
				server.handle(ID, received);
				
			}
			catch(IOException ioe)
			{
				if (running){
					System.err.println("ST:"+ID + " ERROR reading: " + ioe.getMessage());
					running = false;
					server.removeThread(ID);
				}
			}
		}
	}
	
	public void open() throws IOException {
		inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		netLog = new logger(ID+"_servLog");
	}
	public void close(){
		try{
			running = false;
			if (socket != null)    socket.close();
			if (inputStream != null)  inputStream.close();
			if (outputStream != null) outputStream.close();
			if (netLog != null) netLog.close();
			
			netLog=null;
			socket = null;
			inputStream=null;
			outputStream=null;
		}
		catch (IOException ioe){
			System.err.println("Error closing ServerThread "+ID+": "+ioe.getMessage());
		}
	}


	public void send(String msg){
		try {  
			netLog.write("SEND:\t"+msg);
			outputStream.writeUTF(msg);
			outputStream.flush();
		}
		catch(IOException ioe) {  
			System.err.println(ID + " ERROR sending: " + ioe.getMessage());
			server.removeThread(ID);
		}
	}
	
	
	
	

	
}
