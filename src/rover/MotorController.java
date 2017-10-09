/* MotorController
 * Controls arduinos connected to motors through serial connection
 * 
 * Code by Vikram Kashyap, September 2017
 */

package rover;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MotorController {

	SerialConnection forward= null, backward = null;
	boolean good = true;
	
	//Setup the serial controllers
	public MotorController() {
		final String[] comports = {"/dev/ttyUSB0", "/dev/ttyUSB1"};
		
		//Try to find serial connections
		for (String port : comports) {
			//Make connection
			SerialConnection s = new SerialConnection(port, 9600, 8, 1, 0);
			//Wait a sec
			try {
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Check if valid
			if (s.isGood()) {
				//Get id
				String id;
				if (!s.sendMessage("identify".toCharArray()))
					continue;
				id = s.readMessage();
				if (!s.isGood())
					continue;
				
				//Assign based on id
				if (id == null) {
					s.close();
				}
				else if (forward == null && id.equals("forward_")) {
					forward = s;
					System.out.println("Forward connected");
				}
				else if (backward == null && id.equals("backward")) {
					backward = s;
					System.out.println("Backward connected");
				}
			}
		}
		
		if (forward == null || backward == null) {
			good = false;
		}
	}
	
	//State of motor controller
	public boolean isGood() {
		return good;
	}
	
	//Move all wheels at some rate
	public void go(int speed) {
		/*
		String s = Integer.toString(speed);
		forward.sendMessage(s + "  " + s);
		backward.sendMessage(s + "  " + s);*/
		char[] message = {(char) speed, (char) speed, 1, 1, 1, 1, 1, 1};
		System.out.print("Message:" + message + " Len: " + Integer.toString(message.length));
		forward.sendMessage(message);
		backward.sendMessage(message);
		System.out.println(forward.readMessage());
	}
	
	/*
	//On-point left turn (right wheels forward, left wheels backward)
	public void turnLeft(int speed) {
		String s = Integer.toString(speed);
		//forward.sendMessage("-" + s + " " + s);
		//backward.sendMessage("-" + s + " " + s);
	}
	
	//On-point right turn (left wheels forward, right wheels backward)
	public void turnRight(int speed) {
		String s = Integer.toString(speed);
		forward.sendMessage(s + " -" + s);
		backward.sendMessage(s + " -" + s);
	}
	*/
	
	//Cleanup the connections to motor controllers
	public boolean close() {
		return forward.close() && backward.close();
	}
	
}
