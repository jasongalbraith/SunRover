/*Command
 * Makes string commands to send to rover
 * Driver on rover side must be able to handle input given
 */

package control;

public class Commander {
	Controller controller;
	
	public Commander(Controller controller) {
		this.controller = controller;
	}
	
	//Give what sort of inputs wanted
	public int[] getInputTypes() {
		return null;
	}
	
	//Take in input
	public void inputData(Object[] data) {
	}
	
	//Send a command to the controller
	@SuppressWarnings("unused")
	protected void sendCommand(String s) {
		controller.recieveCommand(s);
	}
	
	//Get prompted to make a call to send command
	public void pump() {
	}
}