package control.comms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import tools.AudioPlayer;
import tools.MicTransmitter;

public class ClientAudioHandler {
	
	Socket client;
	InputStream istream;
	OutputStream ostream;
	MicTransmitter mt;
	AudioPlayer ap;
	
	public ClientAudioHandler(String host) {
		try {
			client = new Socket(host, 1302);
			istream = client.getInputStream();
			ostream = client.getOutputStream();
		} catch (UnknownHostException e) {
			System.out.println("CAH: Unknown audio host");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("CAH: IO issue");
			e.printStackTrace();
		}
		
		mt = new MicTransmitter(ostream);
		ap = new AudioPlayer(istream);
	}

}
