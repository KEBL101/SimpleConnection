public class Packet {
	private String message;
	
	public Packet(String message) {
		this.message=message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
