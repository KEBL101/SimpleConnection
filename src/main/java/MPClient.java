import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MPClient {
	private int portSocketTCP = 8080;
	private int portSocketUDP = 8081;
	private String ipAddress = "localhost";
	private Kryo kryo;
	
	private Client client;
	private ClientNetworkListener cnl;
	
	public static void main(String[] args) throws UnknownHostException {
		new MPClient();
	}
	
	private MPClient() throws UnknownHostException {
		client = new Client();
		cnl = new ClientNetworkListener();
		cnl.init(client);
		registerPacket();
		client.addListener(cnl);
		client.start();
		MessagePacket messagePacket = new MessagePacket();
		messagePacket.message="Test connection from client " + InetAddress.getLocalHost().toString();
		System.out.println("Your IP address is " + InetAddress.getLocalHost().toString());
		try {
			client.connect(50000, ipAddress, portSocketTCP, portSocketUDP);
			client.sendTCP(messagePacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void registerPacket() {
		kryo = client.getKryo();
		kryo.register(MessagePacket.class);
	}
}
