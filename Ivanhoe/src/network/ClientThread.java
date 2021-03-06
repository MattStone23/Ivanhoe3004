package network;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientThread extends Thread {
	private Client client = null;
	private Socket socket = null;
	private DataInputStream inputStream = null;
	private boolean running;
	
	public ClientThread(Client c, Socket s){
		super();
		client = c;
		socket = s;
		open();
		start();
	}
	
	//Run thread
	public void run(){
		System.out.println("CLIENT-----client thread Running. Port: "+socket.getPort());
		String received;
		running = true;
		while(running) {
			try {
				// handle responses from server and tell client class what to do
				received = inputStream.readUTF();
				client.handle(received);
			}
			catch(IOException ioe) {
				System.err.println("Listening ERROR: " + ioe.getMessage());
				running = false;
				//client.stop();
			}
			
		}
	}
	
	//Open the thread
	public void open() {
		try {
			inputStream  = new DataInputStream(socket.getInputStream());
			
		}
	    catch(IOException ioe) {
	    	System.err.println("Error getting input stream: " + ioe);
	        //client.stop();
	    }
	}
	
	//Close the thread
	public void close(){
		try {
			if (inputStream != null) inputStream.close();
			
		}
	    catch (IOException ioe){
	    	System.err.println("Error closing input stream: " + ioe);
	    }
		running=false;
	   
	}

	
}
