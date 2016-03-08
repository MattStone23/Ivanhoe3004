package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Util.config;
import Util.timer;
import gameEntities.Card;
import gameEntities.Engine;

public class Server implements Runnable {
	
	private HashMap<Integer, ServerThread> clients = null;
	private HashMap<Integer, Integer> players = null;
	private ServerSocket server = null;
	private Thread       thread = null;
	private int clientCount = 0;
	private int port;
	private int maxClients;
	private int gameState;
	private Engine engine;
	private int prompt=0;
	
	private static final int LOBBY = 0;
	private static final int RUNNING = 1;
//	private static final int ENDED = 2;
	
	public Server(){
		port = config.PORT;
		clients = new HashMap<Integer, ServerThread>();
		players = new HashMap<Integer, Integer>();
		maxClients = config.MAX_CLIENTS;
		
		bindToPort();
	}
	public Server(int portNum){
		port = portNum;
		clients = new HashMap<Integer, ServerThread>();
		players = new HashMap<Integer, Integer>();
		maxClients = config.MAX_CLIENTS;
		
		bindToPort();
	}
	
	private void bindToPort(){
		try{
			System.out.println("SERVER----------Binding to port " + port + ", please wait  ...");
			//server = new ServerSocket(port, 10, InetAddress.getByName(config.IP));
			server = new ServerSocket(port);
			server.setReuseAddress(true);
			System.out.println("IA:\t"+server.getInetAddress());
			System.out.println("LH:\t"+InetAddress.getLocalHost());
			System.out.println("HA:\t"+InetAddress.getLocalHost().getHostAddress());
			start(); 
		}
		catch(IOException ioe){
			System.err.println("Can not bind to port " + port + ": " + ioe.getMessage()); 
		}
	}
	public void start(){
		if (thread == null){
			thread = new Thread(this); 
			thread.start();
			gameState = LOBBY;
			System.out.println("SERVER----------Server started: "+ server +": "+thread.getId());
		}
	}
	
	public void run(){
		while (thread != null){
			try{
				System.out.println("SERVER----------Waiting for a client ..."); 
				addThread(server.accept());
			}
			catch(IOException ioe){
					System.err.println("Server accept error: " + ioe);
			}
		}
	}
	
	private void addThread(Socket socket){

		//accept client		
		System.out.println("SERVER----------Accepting Client: " + socket);
		try	{
			ServerThread serverThread = new ServerThread(this, socket);
			serverThread.open(); 
			serverThread.start();  
			timer.wait(1);
			//Check if should refuse
			if (clientCount >= maxClients){
				//too many clients
				System.err.println("Client refused: maximum " + maxClients + " reached.");
				serverThread.send("REJECT|Full Lobby");
				serverThread.close();
				serverThread = null;
			}
			else if (gameState != LOBBY){
				//LOBBY NOT OPEN
				System.err.println("Client refused: Lobby not open.");
				serverThread.send("REJECT|Lobby Closed");
				serverThread.close();
				serverThread = null;
			}
			else{
				//Lobby is open and not full
				clients.put(serverThread.getID(), serverThread);
				players.put(serverThread.getID(), new Integer(clientCount+1));
				clientCount++;
				System.out.println("ACCEPT|"+players.get(serverThread.getID())+"|"+serverThread.getID());
				serverThread.send("ACCEPT|"+players.get(serverThread.getID())+"|"+serverThread.getID());
			}
		}
		catch(IOException ioe){
			System.err.println("Error opening thread: " + ioe); 
		}
	}
	public synchronized void removeThread(int ID){
		if (clients.containsKey(ID)){
			ServerThread toTerm = clients.get(ID);
			System.out.println("SERVER----------Removing client thread " + ID);
			clients.remove(ID);
			clientCount--;
			
			toTerm.close();
			toTerm = null;
			
		}
	}
	public synchronized void handle(int clientNum, String message){
		//TODO handle stuff
		ServerThread from = clients.get(clientNum);
		String toMessage = "";
		
		String[] args =  message.split("\\|");
		String command = args[0];
		//if quit message
		
		try{
			if (prompt==0){
				switch (command){
				case "QUIT":
					//shut down this client
					from.close();
					break;
				case "SHUTDOWN":
					//shutdown the server
					if (players.get(clientNum).intValue()!=1){
						IllegalArgumentException iae = new IllegalArgumentException("You are not the Host");
						throw iae;
					}
					this.shutdown();
					break;

				case "STARTTOURN":
					//startTourn()
					if(gameHasStarted() && isTheirTurn(clientNum)){
						engine.startTour(args);
						broadCastUpdate();
					}	
					break;

				case "DRAW":
					//draw a card
					if(gameHasStarted() && isTheirTurn(clientNum)){
						toMessage = engine.draw().toString();
						broadCastUpdate();
						clients.get(clientNum).send("CHAT|You drew "+toMessage);
					}	
					break;

				case "PLAY":
					//Play a card
					if(gameHasStarted() && isTheirTurn(clientNum)){
						engine.playCard(args);
						broadCastUpdate();
					}
					break;

				case "WITHDRAW":
					//withdraw from tournament
					if(gameHasStarted() && isTheirTurn(clientNum)){
						boolean tempB = engine.withdraw();
						if(tempB){
							//WITHDRAWN WITH A MAIDEN
							prompt=2;
						}
						from.send("PROMPT|2|"+tempB);
						broadCastUpdate();
					}
					break;

				case "ENDTURN":
					//end turn
					if(gameHasStarted() && isTheirTurn(clientNum)){
						int tempX = engine.endTurn();
						if (tempX==1){
							//SPECIAL CASE: PURPLE TOURNAMENT WON
							int clientID = findClientByPlayerNum(engine.turnNum()+1);
							//set prompt
							prompt=1;
							clients.get(clientID).send("PROMPT|1");
						}
						//TODO handle win tournaments/win
						broadCastUpdate();

						if (tempX==2){
							broadCast("GAMEOVER|"+engine.turnNum()+1);
						}
					}
					break;

				case "IVANHOE":
					//interrupt actioncard
					if(gameHasStarted()){
						from.send("INVALID|NOT IMPLEMENTED");
					}
					break;
				case "WINTOKEN":
					//select which token to win if purple tournament won
					if(!isTheirTurn(clientNum)){
						from.send("INVALID|Not your turn");
						break;
					}

					if (engine!=null)
						broadCastUpdate();
					//TODO
					break;
				case "STARTGAME":
					//start the game
					//If not the host, say invalid
					if (players.get(clientNum).intValue()!=1){
						IllegalArgumentException iae = new IllegalArgumentException("You are not the Host");
						throw iae;
					}

					try{
						engine = new Engine(clientCount);
						gameState = RUNNING;
					}
					catch (IllegalArgumentException iae){
						engine = null;
						throw iae;
					}

					if (engine!=null)
						broadCastUpdate();
					break;

				case "CONNECT":
					toMessage = "CHAT|Player "+players.get(clientNum)+"("+clientNum + ") joined the lobby";
					broadCast(toMessage);
					break;

				case "CHAT":
					toMessage = "CHAT|Player "+players.get(clientNum)+"("+clientNum + ") said: "+args[1];
					broadCast(toMessage);
					break;

				case "PING":
					from.send("CHAT|Ping Received");
					break;
				case "DESC":
					if (args.length==2)
					from.send("DESC|"+new Card(args[1]).getDesc());
					break;
				default:
					//invalid input
					from.send("INVALID|Improper command");
					break;
				}
			}
			else if (prompt==1){//WON A PURPLE TOURN
				if ("WINTOKEN".equals(command)){
					engine.addstone(args);
					prompt=0;
					broadCastUpdate();
				}
			}
			else if (prompt==2){//LOST WITH A MAIDEN
				if ("LOSETOKEN".equals(command)){
					engine.removeToken(args);
					prompt=0;
					broadCastUpdate();
					from.send("PROMPT|2|"+false);
				}
			}
		}catch (IllegalArgumentException iae){
			from.send("INVALID|Improper arguments:"+iae.getMessage());
		}



	}

	public void broadCastUpdate(){
		for (Integer to : clients.keySet()){
			clients.get(to).send("GAMESTATE|"+engine.getGameStateForPlayer(players.get(to)));
		}
	}
	
	private boolean isTheirTurn(int clientID){
		if (gameHasStarted() && players.get(clientID).intValue()-1==engine.turnNum())
			return true;
		throw new IllegalArgumentException("Not your turn.");
	}
	
	private boolean gameHasStarted(){
		if(engine==null)
			throw new IllegalArgumentException("Game has not started yet.");
		return (engine!=null);
	}
	
	public void broadCast(String message){
		for (Integer to : clients.keySet()){
			clients.get(to).send(message);
		}
	}
	
	/** Shutdown the server cleanly */
	public void shutdown() {
		//Set<Integer> keys = clients.keySet();

		if (thread != null) {
			thread = null;
		}

		try {
			for (Integer key : clients.keySet()) {
				//clients.get(key).send("CLOSE");
				clients.get(key).close();
			}
			clients.clear();
			if (server!=null){
				server.close();
				server=null;
			}
		} catch (IOException e) {
			System.err.println("Unexpected Error while shutting down: "+e.getMessage());
		}
		System.out.println("SERVER----------Server Shutdown cleanly "+ server);
		
	}
	private int findClientByPlayerNum(int p){
		for (Integer key : players.keySet()){
			if (players.get(key)==p){
				return key;
			}
		}
		return -1;
	}
}
