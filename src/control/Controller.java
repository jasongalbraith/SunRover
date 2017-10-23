/*Controller
 * Main execution thread for remote controlling computer
 */

package control;

import java.util.Scanner;

public class Controller {
	Transciever tr;
	ManualCommander mc;

	public Controller() {
		Scanner stdin = new Scanner(System.in);
		boolean done = false;
		
		tr = new Transciever("sharky", 1300);
		mc = new ManualCommander(this);
		
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