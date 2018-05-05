package Client;

import java.net.*;
import java.util.*;
import java.io.*;

public class Client {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket cSocket;
	private String str = "";
	Scanner scanner;
	byte[] data;
	int size, len;
	
	public Client(String ServerIP, int port) throws IOException {
		scanner = new Scanner(System.in);
		
		//TODO Task 1: Create a socket for connecting the server
		 cSocket = new Socket(InetAddress.getByName(ServerIP), port);
		
		//TODO Task 2: Open streams for I/O
		in = new DataInputStream(cSocket.getInputStream());
		out = new DataOutputStream(cSocket.getOutputStream());
		
		System.out.println("Connected to server using local port: " + cSocket.getLocalPort());
		send();
	}
	
	public void send() throws IOException{
		while(!str.equalsIgnoreCase("BYE BYE")) {
			System.out.print("Client.Client: ");
			str = scanner.nextLine();
			data = str.getBytes();
			
			//TODO Task 3: send data to the server
			out.writeInt(data.length);
			out.write(data);
			
			//TODO Task 4: Receive data and store in the byte array
			size = in.readInt();
			data = new byte[size];
			do {
				len = in.read(data, data.length - size, size);
				size -= len;
			} while (size > 0);
			
			System.out.println(new String(data));
		}
		in.close();
		out.close();
		cSocket.close();
		scanner.close();
	}
	
	public void recieve() throws IOException {
		while(!str.equalsIgnoreCase("BYE BYE")) {
			str = scanner.nextLine();
			data = str.getBytes();
			
			//TODO Task 3: send data to the server
			out.writeInt(data.length);
			out.write(data);
			
			//TODO Task 4: Receive data and store in the byte array
			size = in.readInt();
			data = new byte[size];
			do {
				len = in.read(data, data.length - size, size);
				size -= len;
			} while (size > 0);
			
			System.out.println(new String(data));
		}
		in.close();
		out.close();
		cSocket.close();
		scanner.close();
	}
	

	public static void main(String[] args) throws IOException {
			new Client("127.0.0.1", 8999);
	}
}

