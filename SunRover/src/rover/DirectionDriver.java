/*DirectionDriver
 * Creates messages for MotorController from direction commands
 */

package rover;

import java.util.Arrays;

import tools.DataHandler;

public class DirectionDriver extends Driver{
	byte[][] command = new byte[2][2];
	
	public DirectionDriver(DataHandler dh) {
		super(dh);
	}

	public void inputCommand(String s) {
		if (s.startsWith("MOTOR")) {
			Arrays.fill(command[0], (byte) 1);
			Arrays.fill(command[1], (byte) 1);
			
			if (s.equals("MOTOR_UP")) {
				//mc.go(127);
				command[0][0] = command[0][1] = (byte) 127;
				command[1] = command[0];
			}
			else if (s.equals("MOTOR_DOWN")) {
				command[0][0] = command[0][1] = (byte) -127;
				command[1] = command[0];
			}
			else if (s.equals("MOTOR_LEFT")) {
				command[0][0] = (byte) 127;
				command[0][1] = (byte) -command[0][0];
				command[1] = command[0];
			}
			else if (s.equals("MOTOR_RIGHT")) {
				command[0][0] = (byte) -127;
				command[0][1] = (byte) -command[0][0];
				command[1] = command[0];
			}
			else if (s.equals("MOTOR_STOP")) {
				command[0][0] = command[0][1] = (byte) 0;
				command[1] = command[0];
			}
			
			sendMotorVals(command);
		}
		
		else if (s.startsWith("SERVO")) {
			if (s.equals("SERVO_UP")) {
				sendServoVals(0, 1800);
			}
			else if (s.equals("SERVO_DOWN")) {
				sendServoVals(0, 200);
			}
		}
	}
	
}
