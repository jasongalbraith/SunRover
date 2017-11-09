package rover.webcam;

import java.awt.image.BufferedImage;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

import org.bytedeco.javacv.*;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import control.webcam.RoverImage;

public class SunRoverWebcam implements Runnable {

	final int FIRST_WEBCAM = 0;
	final int SECOND_WEBCAM = 1;
	int threadToStart = FIRST_WEBCAM;
	ServerSocket server;
	Socket client;
	ObjectOutputStream stream1;
	ObjectOutputStream stream2;
	FrameGrabber grabber1 = new OpenCVFrameGrabber(0);
	FrameGrabber grabber2 = new OpenCVFrameGrabber(1);
	BufferedImage bimg1;
	BufferedImage bimg2;
	RoverImage rimg1;
	RoverImage rimg2;
	OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
	Java2DFrameConverter paintConverter = new Java2DFrameConverter();

	public SunRoverWebcam() {
		try {
			server = new ServerSocket(1301);
			System.out.println("Waiting...");
			client = server.accept();
			System.out.println("Got Socket 1");
			stream1 = new ObjectOutputStream(client.getOutputStream());
			client = server.accept();
			System.out.println("Got Socket 2");
			stream2 = new ObjectOutputStream(client.getOutputStream());
			Thread t = new Thread(this);
			t.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SunRoverWebcam();
	}

	public void run() {
		if (threadToStart == FIRST_WEBCAM) {
			try {
				// Start grabber to capture video
				grabber1.start();
				IplImage img1;
				while (true) {
					img1 = converter.convert(grabber1.grab());
					if (img1 != null) {
						// Flip image horizontally
						//cvFlip(img1, img1, 1);
						// Show video frame in canvas
						Frame frame1 = converter.convert(img1);
						bimg1 = paintConverter.getBufferedImage(frame1, 1);
						stream1.writeUnshared(new ImageIcon(bimg1));
						stream1.flush();
						stream1.reset();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (threadToStart == SECOND_WEBCAM) {
			try {
				// Start grabber to capture video
				grabber2.start();
				IplImage img2;
				while (true) {
					img2 = converter.convert(grabber2.grab());
					if (img2 != null) {
						// Flip image horizontally
						//cvFlip(img2, img2, 1);
						// Show video frame in canvas
						Frame frame2 = converter.convert(img2);
						bimg2 = paintConverter.getBufferedImage(frame2, 1);
						stream2.writeUnshared(new ImageIcon(bimg2));
						stream2.flush();
						stream2.reset();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}