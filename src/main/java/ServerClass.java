import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class ServerClass extends Listener {
	
	private static Server server;
	private static int udpPort = 27960, tcpPort = 27961;
	private static String ip;
	
	static {
		try {
			ip = InetAddress.getLocalHost().getHostAddress().trim();
			System.out.println(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Creating the server...");
		server = new Server();
		server.getKryo().register(Message.class);
		server.bind(tcpPort);
		server.start();
		server.addListener(new ServerClass());
		System.out.println("Server is operational!");
	}
	
	public void connected (Connection c) {
		System.out.println("Received a connection from " + c.getRemoteAddressTCP().getHostString() + " at " + new Date().toString());
		Message message = new Message("Hello friend! The time is now " + new Date().toString());
		c.sendTCP(message);
	}
	
	public void disconnected (Connection c) {
		System.out.println(c.getRemoteAddressTCP().getHostString() + " has disconnected at " + new Date().toString());
	}
	
	public void received (Connection c, Object o) {
		// :)
	}
}
