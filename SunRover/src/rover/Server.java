/* Server
 * Serves a socket to connect to SunRover
 */

package rover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import tools.DataHandler;
import tools.DataSource;

public class Server extends Thread {
		
	ServerSocket serversocket;
	Socket clientsocket;
	boolean good = false;
	
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
		if (clientsocket == null || clientsocket.isClosed())
			good = false;
		return good;
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
	
	//Get input stream
	public InputStream getInputStream() {
		InputStream is = null;
		
		if (clientsocket == null) {
			good = false;
			return null;
		}

		try {
			is = clientsocket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return is;
	}
	
	//Get output stream
	public OutputStream getOutputStream() {
		OutputStream os = null;
		
		if (clientsocket == null) {
			good = false;
			return null;
		}
		
		try {
			os = clientsocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return os;
	}
	
	//Wait for input
	public void run() {		
		while (clientsocket == null || clientsocket.isClosed()) {
			try {
				clientsocket = serversocket.accept();
				good = true;
				System.out.println("Connected");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}