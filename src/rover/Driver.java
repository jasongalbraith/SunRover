/*Driver
 * Interface to take in data and gives motor controll instructions
 */

package rover;

public interface Driver {
	
	//Take in data to process
	public void getData(byte[] data);
	
	//Give a command
	public byte[][] formulateCommand();
	
}
