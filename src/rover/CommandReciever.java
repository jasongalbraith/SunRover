/* CommandReciever
 * Inputs strings through a socket connection to the controlling computer
 * 
 * Code by Vikram Kashyap, September 2017
 */

package rover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

public class CommandReciever extends Thread{
	Socket socket;
	BufferedReader in;
	Queue<String> buffer = new LinkedList<String>();
	boolean good = true;
	
	public CommandReciever(String hostname, int port) {
			//Try to open socket
			try {
				socket = new Socket(hostname, port);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (UnknownHostException e) {
				good = false;
				System.out.println("Can't find the host");
			} catch (IOException e) {
				good = false;
				System.out.println("Couldn't connect to host");
			}
	}
	
	//State of connection
	public boolean isGood() {
		return good;
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
	
	//Close the connection
	public boolean close() {
		try {
			socket.close();
		} catch (IOException e) {
			return false;
		}
	
		return true;
	}
	
	//Read a line from the buffer
	public String readLine() {
		String line;
		
		synchronized(buffer)  {
			line = buffer.poll();
		}
		
		return line;
	}
	
	//Wait for a message as a thread
	public void run() {
		String line;
		
		while (isGood()) {
			line = getMessage();
			if (line != null) {
				buffer.add(line);
				//System.out.println("*" + buffer.size());
			}
		}
	}
}
