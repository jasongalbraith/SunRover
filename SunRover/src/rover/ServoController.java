package rover;

import java.util.concurrent.TimeUnit;

import tools.DataHandler;
import tools.DataReciever;

public class ServoController implements DataReciever {
	
	public static final int SERVO_SETTARGET = 0;
	
	private static final int[] REQUESTED_DATA = {DataHandler.DTYPE_SERVOVALS};
	
	SerialConnection maestro;	//Connection to Polulu Mini-Maestro
	boolean good = false;	//State of connection
	
	public ServoController() {
		final String[] comports = {"COM1", "COM2", "COM3", "COM4", "COM5"};
		
		//Make connection
		maestro = new SerialConnection("COM1", 9600, 8, 1, 0);
		//Wait a sec
		try {
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (maestro.isGood())
			good = true;
	}
	
	//Make command to set certain pulse on a channel and send command
	private void setTarget(int channel, int pulse) {
		byte[] command = new byte[4];
		command[0] = (byte) 0x84;										//Command byte
		command[1] = (byte) channel;									//Channel byte
		command[2] = (byte) (((short) pulse) & 0b0000000001111111);		//Low bits of pulse
		command[3] = (byte) (((short) pulse) >> 7);						//High bits of pulse
		
		sendCommand(command);
	}
	
	//Send command to the maestro
	private void sendCommand(byte[] command) {
		maestro.sendMessage(command);
	}
	
	//State of ServoController
	public boolean isGood() {
		return good;
	}
	
	//Give data to receive
	public int[] getDataTypes() {
		return REQUESTED_DATA;
	}

	public void recieveData(int type, Object data) {
		int[] vals = (int[]) data;
		
		if (vals[0] == SERVO_SETTARGET) {
			setTarget(vals[1], vals[2]);
		}
	}

}
