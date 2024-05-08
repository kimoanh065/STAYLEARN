package Design;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.print.DocFlavor.INPUT_STREAM;

public class ClientHanDler implements Runnable {
	private Socket mysocket;
	private ChatServer chatserver;
	String staffname;
	private InputStream input;
	private OutputStream output;

	public ClientHanDler(Socket mysocket, ChatServer chatserver) {

		this.mysocket = mysocket;
		
		this.chatserver =chatserver;
		try {
			this.input = mysocket.getInputStream();
			this.output = mysocket.getOutputStream();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		Form_Login dk = new Form_Login();
		staffname = Form_Login.staffName;
		// TODO Auto-generated method stub
		try {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buffer)) != -1) {
				String message = new String(buffer, 0, bytesRead);
				chatserver.broadcasrmessage(message);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void sendmessage(String message) {
		try {
			output.write(message.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	

}