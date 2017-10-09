/* SunRover
 * Main execution thread for the on-board SunRover brain computer
 * 
 * Code by Vikram Kashyap, September 2017
 */

package rover;

public class SunRover {
	public static void main(String[] args) {
		MotorController mc = new MotorController();
		Server server = new Server(1300);
		boolean done = false;
		
		server.start();
		
		
		while (!done) {
			String input;
			
			if (server.isGood()) {
				input = server.readLine();
				
				if (input != null) {
					System.out.println(input);
					mc.go(Integer.parseInt(input));
				}
			}
		}
		
		System.out.print("Closing");
		
		mc.close();
		server.close();
	}
}
