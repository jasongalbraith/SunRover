/*DirectionDriver
 * Creates messages for MotorController from direction commands
 */

package rover;

import java.util.Arrays;

import tools.DataHandler;

public class DirectionDriver extends Driver{
	byte[][] command = new byte[2][8];
	MotorController mc;
	
	public DirectionDriver(DataHandler dh) {
		super(dh);
	}

	public void inputCommand(String s) {
		Arrays.fill(command[0], (byte) 1);
		Arrays.fill(command[1], (byte) 1);
		
		if (s.equals("UP")) {
			//mc.go(127);
			command[0][0] = command[0][1] = (byte) 127;
			command[1] = command[0];
		}
		else if (s.equals("DOWN")) {
			command[0][0] = command[0][1] = (byte) -127;
			command[1] = command[0];
		}
		else if (s.equals("LEFT")) {
			command[0][0] = (byte) 127;
			command[0][1] = (byte) -command[0][0];
			command[1] = command[0];
		}
		else if (s.equals("RIGHT")) {
			command[0][0] = (byte) -127;
			command[0][1] = (byte) -command[0][0];
			command[1] = command[0];
		}
		else if (s.equals("STOP")) {
			command[0][0] = command[0][1] = (byte) 0;
			command[1] = command[0];
		}
		
		sendMotorVals(command);
	}
	
}
