import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerNetworkListener extends Listener {
	public ServerNetworkListener() {
	
	}
	
	public void connected(Connection c) {
		System.out.println(c.getRemoteAddressTCP().getHostName() + " has connected");
		Packet welcome = new Packet("Welcome!");
		c.sendTCP(welcome);
	}
	
	public void disconnected(Connection c) {
		System.out.println("Someone has disconnected");
	}
	
	public void received(Connection c, Object o) {
		if (o instanceof Packet) {
			Packet packet = (Packet) o;
			System.out.println(c.getRemoteAddressTCP().getHostName() +": " + packet.toString());
		}
	}
}
