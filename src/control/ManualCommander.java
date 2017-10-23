/*ManualCommander
 * Creates commands for SunRover from key inputs
 */

package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JFrame;

public class ManualCommander extends JFrame implements KeyListener{
	String command;
	Controller controller;
	
	public ManualCommander(Controller c) {
		controller = c;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		setVisible(true);
	}

	public void keyPressed(KeyEvent k) {
		
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
		
		sendCommand();
	}

	public void keyReleased(KeyEvent k) {
		/*Do nothing*/
	}

	public void keyTyped(KeyEvent k) {
		/*Do nothing*/
	}
	
	private void sendCommand() {
		controller.recieveCommand(command);
	}
}
