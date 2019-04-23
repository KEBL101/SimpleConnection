import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MPServer {
	int serverPort = 25565;
	Server server;
	ServerNetworkListener snl;
	Kryo kryo;
	
	public static void main(String[] args) throws UnknownHostException {
		new MPServer();
	}
	
	public MPServer() throws UnknownHostException {
		server = new Server();
		System.out.println("Your IP is " + InetAddress.getLocalHost().toString());
		snl = new ServerNetworkListener();
		server.addListener(snl);
		try {
			server.bind(serverPort, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		registerPackets();
		server.start();
		try {
			server.update(1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void registerPackets() {
		kryo = new Kryo();
		kryo.register(Packet.class);
	}
}
