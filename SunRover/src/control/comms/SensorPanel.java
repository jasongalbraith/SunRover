package control.comms;

import java.awt.Graphics;
import javax.swing.JPanel;


public class SensorPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int[] sensors;
	
	public SensorPanel(int[] sensorsIn) {
		sensors = sensorsIn;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(50, 30, 60, 100);
		g.drawLine(80, 30, 80, 50);
		g.drawLine(80, 30, 85, 35);
		g.drawLine(80, 30, 75, 35);
		g.drawString(sensors[WebcamDisplay.FORWARD]+" F", 80, 10);
		g.drawString(sensors[WebcamDisplay.LEFT]+" L", 20, 90);
		g.drawString(sensors[WebcamDisplay.RIGHT]+" R", 120, 90);
		g.drawString(sensors[WebcamDisplay.BACKWARD]+" B", 80, 150);
		g.drawString(sensors[WebcamDisplay.FORWARD_DOWN]+" FD", 80, 20);
		g.drawString(sensors[WebcamDisplay.BACKWARD_DOWN]+" BD", 80, 160);
		g.drawString(sensors[WebcamDisplay.COMPASS]+" COM", 60, 90);
	}
	
}
