/*Command
 * Makes commands to send to rover
 * Driver on rover side must be able to handle input given
 */

package control;

public interface Commander {
	
	//Give data to be sent
	public String getData();
	
}
