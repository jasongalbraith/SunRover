/*Controller
 * Main execution thread for remote controlling computer
 */

package control;

import java.util.Scanner;

import control.comms.ClientAudioHandler;
import control.comms.WebcamDisplay;
import tools.DataHandler;

public class Controller {
	public static final String ROVER_HOSTNAME = "172.23.52.194";//"SHS-10L4331FD";
	
	ControlFrame cf;
	Transciever tr;
	ManualCommander mc;
	DataHandler dh;
	WebcamDisplay wd;
	ClientAudioHandler ca;

	public Controller() {
		Scanner stdin = new Scanner(System.in);
		boolean done = false;
		
		tr = new Transciever(ROVER_HOSTNAME, 1300);
		mc = new ManualCommander(this);
		dh = new DataHandler();
		cf = new ControlFrame(dh);
		wd = new WebcamDisplay(cf);
		ca = new ClientAudioHandler(ROVER_HOSTNAME);
		
		
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
		System.out.println(command);
		tr.sendMessage(command);
	}
	
}
