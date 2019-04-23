import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientNetworkListener extends Listener {
	private Client client;
	
	public void init(Client client) {
		this.client=client;
	}
	
	public void connected(Connection connection) {
		System.out.println("[CLIENT] >> You have connected to " + connection.getRemoteAddressTCP().getHostName());
		Packet message = new Packet("Hello!");
		client.sendTCP(message);
		System.out.println("Hello message sent to server");
	}
	
	public void disconnected(Connection connection) {
		System.out.println("[CLIENT] >> You have disconnected");
	}
	
	public void received(Connection connection, Object object) {
		if (object instanceof Packet) {
			Packet message = (Packet) object;
			System.out.println("[SERVER] >> " + message.toString());
		}
	}
}
