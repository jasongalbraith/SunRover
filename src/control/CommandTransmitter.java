/* CommandTransmitter
 * 
 * OUTDATED
 * 
 * Sends strings through a socket connection to the on-broad SunRover brain computer
 */

package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandTransmitter {

	ServerSocket serversocket;
	Socket clientsocket;
	PrintWriter out;
	boolean good = true;
	
	public CommandTransmitter(int port) {
		//Try to open a port and wait until connected
		try {
			serversocket = new ServerSocket(port);
			clientsocket = serversocket.accept();
			
			out = new PrintWriter(clientsocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
			good = false;
		}
		
		System.out.println("Connected");
	}
	
	//State of connection
	public boolean isGood() {
		return good;
	}
	
	//Send a string to the client
	public void sendMessage(String s) {
		out.println(s);
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
}
