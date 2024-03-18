package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.Buffer;

public class ChatServer {
	
	static final int PORT = 4000;
	private ServerSocket serversocket = null;
	private final String SERVER_ADDRESS = "127.0.0.1";
	private BufferedReader in;
	
	public void start() throws IOException {
		serversocket = new ServerSocket(PORT);
		System.out.println("Server Started in port "+PORT);
		clientConnectionLoop();
	}
	
	private void clientConnectionLoop() throws IOException {
		ClienteSocket clientesocket = new ClienteSocket(serversocket.accept());
		new Thread(()->{
			try {
				clienteMessageLoop(clientesocket);
			}catch(Exception e) {
				
			}
		}).start();
	}
	
	public void clienteMessageLoop(ClienteSocket clientesocket) throws IOException{
		String msg;
		try {
			while((msg=clientesocket.getMensage())!=null) {
				if("sair".equalsIgnoreCase(msg)) {
					return;
				}
				System.out.printf("Msg received of client%s: %s\n", clientesocket.getRemoteSocketAddress(),msg);
				
			}
		}finally {
			clientesocket.close();
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ChatServer server = new ChatServer();
		server.start();

	}

}
