/*ManualCommander
 * Creates commands for SunRover from key inputs
 */

package control;

import java.awt.event.KeyEvent;

import tools.DataHandler;
import tools.DataReciever;

public class ManualCommander extends Commander implements DataReciever{
	public static final int[] input_types = {DataHandler.DTYPE_KEYPRESS_SOURCE1};
	String command;
	
	public ManualCommander(Controller c) {
		super(c);
	}

	private void processKey(KeyEvent k) {
		
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			command = "UP";
		}
		else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
			command = "DOWN";
		}
		else if (k.getKeyCode() == KeyEvent.VK_LEFT) {
			command = "LEFT";
		}
		else if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
			command = "RIGHT";
		}
		else if (k.getKeyCode() == KeyEvent.VK_SPACE) {
			command = "STOP";
		}
		
		sendCommand(command);
	}

	public int[] getDataTypes() {
		return input_types;
	}
	
	public void pump() {
		sendCommand(command);
	}

	public void recieveData(int type, Object data) {
		if (type == input_types[0]) {
			processKey((KeyEvent) data);
		}
	}
}
