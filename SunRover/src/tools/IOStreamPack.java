package tools;

import java.io.InputStream;
import java.io.OutputStream;

public class IOStreamPack {
	private InputStream is;
	private OutputStream os;
	
	
	public InputStream getInputStream() {
		return is;
	}
	public void setInputStream(InputStream is) {
		this.is = is;
	}
	public OutputStream getOutputStream() {
		return os;
	}
	public void setOutputStream(OutputStream os) {
		this.os = os;
	}
}
