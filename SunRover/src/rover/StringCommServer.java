package rover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import tools.DataHandler;
import tools.DataSource;

public class StringCommServer extends Thread implements DataSource {
	
	private static final int[] OFFERED_TYPES = {DataHandler.DTYPE_COMMANDERSTRING};
	
	Server server;
	PrintWriter out;
	BufferedReader in;
	Queue<String> buffer = new LinkedList<String>();
	boolean running = true;
	boolean good;
	
	public StringCommServer(int port) {
		server = new Server(port);
	}
	
	//State of commserver
	boolean isGood() {
		return good;
	}
	
	//Send a string to the client
	public void sendMessage(String s) {
		out.println(s);
	}
	
	//Receive a string over the connection
	public String getMessage() {
		
		String line = null;
		
		try {
			line = in.readLine();
		} catch (IOException e) {
			good = false;
			e.printStackTrace();
		}
				
		return line;
	}
	
	//Read a line from the buffer
	public String readLine() {
		String line;
		
		synchronized(buffer)  {
			line = buffer.poll();
		}
		
		return line;
	}
	
	public int[] getOfferedDataTypes() {
		return OFFERED_TYPES;
	}
	
	public void run() {
		String line;
		
		while (running) {
			good = true;
			
			while (server.isGood()) {
				line = getMessage();
				if (line != null) {
					buffer.add(line);
					//System.out.println("*" + buffer.size());
				}
			}
			
			good = false;
			
			while (!server.isGood());
			
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			out = new PrintWriter(server.getOutputStream());
		}
	}
	
	public void close() {
		server.close();
	}
}
