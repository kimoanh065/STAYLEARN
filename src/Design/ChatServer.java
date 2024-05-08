package Design;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
private static final int port = 8000;
	
	private List<ClientHanDler> clients = new ArrayList<>();

	private void startSever() {
		try {
			ServerSocket serversocket = new ServerSocket(port);
			int i =1;
			while (true) {
				Socket clientsocket = serversocket.accept();
				System.out.println("máy đã kết nối" + i);
				ClientHanDler clienthander = new ClientHanDler(clientsocket , this);
				clients.add(clienthander);
				new Thread(clienthander).start();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void broadcasrmessage(String message) {
		for (ClientHanDler client : clients) {
			client.sendmessage(message);
		}
	}
	public static void main(String[] args) {
		ChatServer chatserver = new ChatServer();
		chatserver.startSever();
	}
}
