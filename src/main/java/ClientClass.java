import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ClientClass extends Listener {
	
	private static Client client;
	private static String ip = "localhost";
	private static int tcpPort=27961, udpPort=27960;
	private static boolean messageReceived = false;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		client = new Client();
		client.getKryo().register(Message.class);
		client.connect(50000, ip, tcpPort, udpPort);
		System.out.println("Connected!");
		Message hello = new Message("Hello!");
		client.sendTCP(hello);
		client.addListener(new ClientClass());
		System.out.println("The program is now waiting for a packet...");
		while (!messageReceived) {
			Thread.sleep(1000);
		}
		System.out.println("Exiting...");
		System.exit(0);
	}
	
	public void received(Connection c, Object o) {
		if (o instanceof Message) {
			Message message = (Message) o;
			System.out.println("Received a message from the host: ");
			System.out.println(message.toString());
			System.out.println(new Date().toString());
			
			messageReceived=true;
		}
	}
}