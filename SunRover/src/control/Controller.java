/*Controller
 * Main execution thread for remote controlling computer
 */

package control;

import java.util.Scanner;

import control.webcam.WebcamDisplay;
import tools.DataHandler;

public class Controller {
	ControlFrame cf;
	Transciever tr;
	ManualCommander mc;
	DataHandler dh;
	WebcamDisplay wd;

	public Controller() {
		Scanner stdin = new Scanner(System.in);
		boolean done = false;
		
		tr = new Transciever("SHS-10L4331FD", 1300);
		mc = new ManualCommander(this);
		dh = new DataHandler();
		cf = new ControlFrame(dh);
		wd = new WebcamDisplay(cf);
		
		dh.addReciever(mc);
		
		if (!tr.isGood()) {
			System.out.println("Couldn't open port");
			System.exit(1);
		}
			
		while (!done) {
			String input = stdin.nextLine();
			if (input.equals("q"))
				done = true;
			else
				tr.sendMessage(input);
			
			if (!tr.isGood()) {
				done = true;
			}
		}
		
		tr.sendMessage("\0");
		
		tr.close();
		
		stdin.close();
	}
	
	public static void main(String[] args) {
		new Controller();
	}

	public void recieveCommand(String command) {
		tr.sendMessage(command);
	}
	
}