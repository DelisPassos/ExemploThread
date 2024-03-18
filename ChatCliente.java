package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.net.UnknownHostException;

public class ChatCliente {
	
	private static final int PORT = 4000;
	private ClienteSocket clientesocket = null;
	private final String SERVER_ADDRESS = "127.0.0.1";
	
	private Scanner scanner;
	
	public ChatCliente() {
		scanner = new Scanner(System.in);
	}
	
	public void start() throws UnknownHostException, IOException{
		clientesocket = new ClienteSocket(new Socket(SERVER_ADDRESS, ChatServer.PORT));
		System.out.println("Cliente connected to server in"+SERVER_ADDRESS+":"+ChatServer.PORT);
		messageLoop();
	}
	
	public void messageLoop() {
		String msg = null;
		do {
			System.out.println("Write your messege ou exit to leave");
			msg = scanner.nextLine();
			clientesocket.send(msg);
		}while(!msg.equalsIgnoreCase("exit"));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatCliente cliente = new ChatCliente();
		try {
			cliente.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client exit!");

	}

}
