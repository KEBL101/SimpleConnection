public class Message {
	private String message;
	
	Message(String message) {
		this.message=message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
