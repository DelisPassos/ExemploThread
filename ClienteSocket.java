package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class ClienteSocket {
	private PrintWriter out;
	private Socket socket;
	private BufferedReader in;
	
	public ClienteSocket (Socket socket) throws IOException {
		this.socket = socket;
		System.out.println("Criente "+socket.getRemoteSocketAddress()+" conectou");
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new PrintWriter(socket.getOutputStream());
	}
	
	public SocketAddress getRemoteSocketAddress() {
		return socket.getRemoteSocketAddress();
	}
	
	public String getMensage() throws IOException{
		return in.readLine();
	}
	
	public boolean send(String msg) {
		out.println(msg);
		return out.checkError();
	}
	
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

}
