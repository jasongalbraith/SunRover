package control.comms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import tools.AudioPlayer;
import tools.IOStreamPack;
import tools.MicTransmitter;

public class ClientAudioHandler {
	
	Socket client;
	InputStream istream;
	OutputStream ostream;
	IOStreamPack iopack;
	MicTransmitter mt;
	AudioPlayer ap;
	
	public ClientAudioHandler(String host) {
		try {
			client = new Socket(host, 1302);
			istream = client.getInputStream();
			ostream = client.getOutputStream();
			iopack = new IOStreamPack(istream, ostream);
		} catch (UnknownHostException e) {
			System.out.println("CAH: Unknown audio host");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("CAH: IO issue");
			e.printStackTrace();
		}
		
		mt = new MicTransmitter(iopack);
		ap = new AudioPlayer(iopack);
	}

}
