import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MPClient {
	int portSocket = 8080;
	String ipAddress = "169.254.157.238";
	Kryo kryo;
	
	public Client client;
	private ClientNetworkListener cnl;
	
	public static void main(String[] args) throws UnknownHostException {
		new MPClient();
	}
	
	public MPClient() throws UnknownHostException {
		client = new Client();
		cnl = new ClientNetworkListener();
		cnl.init(client);
		registerPacket();
		client.addListener(cnl);
		client.start();
		Packet packet = new Packet("Test connection from client");
		
		try {
			client.connect(50000, ipAddress, portSocket);
			client.sendTCP(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void registerPacket() {
		kryo = client.getKryo();
		kryo.register(Packet.class);
	}
}
