/* Server
 * Serves a socket to connect to SunRover
 */

package rover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import tools.DataHandler;
import tools.DataSource;

public class Server extends Thread implements DataSource{
	
	ServerSocket serversocket;
	Socket clientsocket;
	PrintWriter out;
	BufferedReader in;
	Queue<String> buffer = new LinkedList<String>();
	boolean good = true;
	
	public Server(int port) {
		//Try to open a port and wait until connected
		try {
			serversocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			good = false;
		}
	}
	
	//State of connection
	public boolean isGood() {
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
		
		if (line == null)
			good = false;
		
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
	
	//Close the connection and port
	public boolean close() {
		try {
			clientsocket.close();
			serversocket.close();
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	//Wait for input
	public void run() {
		String line;
		
		while (true) {
			try {
				clientsocket = serversocket.accept();
				out = new PrintWriter(clientsocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
				good = true;
				System.out.println("Connected");
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (isGood()) {
				line = getMessage();
				if (line != null) {
					buffer.add(line);
					//System.out.println("*" + buffer.size());
				}
			}
		}
	}

	public int getDataType() {
		return DataHandler.DTYPE_COMMANDERSTRING;
	}
}