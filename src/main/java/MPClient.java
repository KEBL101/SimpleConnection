import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;


import java.io.IOException;

public class MPClient {
	int portSocket = 25565;
	String ipAddress = "localhost";
	Kryo kryo;
	
	public Client client;
	private ClientNetworkListener cnl;
	
	public static void main(String[] args) {
		new MPClient();
	}
	
	public MPClient() {
		client = new Client();
		cnl = new ClientNetworkListener();
		cnl.init(client);
		registerPacket();
		client.addListener(cnl);
		client.start();
		
		try {
			client.connect(50000, ipAddress, portSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void registerPacket() {
		kryo = client.getKryo();
		kryo.register(Packet.class);
	}
}
