/* SunRover
 * Main execution thread for the on-board SunRover brain computer
 */

package rover;

import rover.comms.WebcamServer;
import tools.DataHandler;

public class SunRover {
	public static void main(String[] args) {
		DataHandler dh = new DataHandler();
		//MotorController mc = new MotorController();
		//ServoController sc = new ServoController("COM4");
		//ServoMotorController sm = new ServoMotorController("COM5");
		StringCommServer commserver = new StringCommServer(1300);
		Driver driver = new DirectionDriver(dh);
		//WebcamServer ws = new WebcamServer();
		boolean done = false;
		
		dh.addSource(commserver);
		//dh.addSource(driver);
		dh.addReciever(driver);
		//dh.addReciever(mc);
		//dh.addReciever(sc);
		//dh.addReciever(sm);
		
		commserver.start();
		/*
		if (sc.isGood())
			System.out.println("Connected to maestro");
		if (sm.isGood())
			System.out.println("Connected to servomotor controller");
		*/
		
		while (!done) {
			String input;
			
			if (commserver.isGood()) {
				input = commserver.readLine();
				
				System.out.println(input);
				
				if (input != null) {
					dh.pushData(DataHandler.DTYPE_COMMANDERSTRING, input);
				}
			}
		}
		
		System.out.print("Closing");
		
		//mc.close();
		commserver.close();
	}
}
