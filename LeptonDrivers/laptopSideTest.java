import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class laptopSideTest {
	public static final int PORT = 3636;
	public static final int WIDTH = 32;
	public static final int HEIGHT = 28;
	public static void main (String[] args) {
		try {
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(500, 500);
			BufferedImage back = new BufferedImage(0,0,BufferedImage.TYPE_INT_ARGB);
			JLabel jl = new JLabel(new ImageIcon(back));
			f.setContentPane(jl);
			f.setVisible(true);
			ServerSocket ss = new ServerSocket(PORT);
			Socket conn = ss.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String s;
			while((s=in.readLine())!=null) {
				BufferedImage img = strToImg(s);
				back = img;
				jl.setIcon(new ImageIcon(back));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static BufferedImage strToImg(String ss) {
		char[] s = ss.toCharArray();
		BufferedImage b = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < s.length; i++) {
			b.setRGB((int)Math.floor(i/28), i%28, s[i]);
		}
		return b;
	}
}
