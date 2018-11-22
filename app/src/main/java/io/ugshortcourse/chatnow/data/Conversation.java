package io.ugshortcourse.chatnow.data;

public class Conversation extends Model {
	private String sender, recipient, message, key;
	private boolean delivered;
	private long timestamp;
	
	public Conversation() {
	}
	
	public Conversation(String sender, String recipient, String message) {
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
		this.delivered = false;
		this.key = "";
		this.timestamp = System.currentTimeMillis();
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isDelivered() {
		return delivered;
	}
	
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String getName() {
		return message;
	}
	
	@Override
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return "Conversation{" +
				"sender='" + sender + '\'' +
				", recipient='" + recipient + '\'' +
				", message='" + message + '\'' +
				", key='" + key + '\'' +
				", delivered=" + delivered +
				", timestamp=" + timestamp +
				'}';
	}
}
