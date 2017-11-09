/*Driver
 * Interface to take in data and gives motor controll instructions
 */

package rover;

public class Driver {
	public static final String NAME = "DEAD_DRIVER";
	public static final String[] REQUESTED_DATA = {};
	
	public Driver() {
	}
	
	//Take in data to process
	public void inputData(Object data) {
		
	}
	
	//Give a command
	public byte[] formulateCommand() {
		byte[] command = {0, 0, 0, 0};
		return command;
	}
}
