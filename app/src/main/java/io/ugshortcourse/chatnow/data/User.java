package io.ugshortcourse.chatnow.data;

import java.util.Date;

public class User extends Model {
	private String name, profile, phone, key;
	private boolean online;
	private Date timestamp,lastSeen;
	
	public User() {
	}
	
	public User(String name, String profile, String phone, String key) {
		this.name = name;
		this.profile = profile;
		this.phone = phone;
		this.key = key;
		this.timestamp = new Date(System.currentTimeMillis());
		this.lastSeen = new Date(System.currentTimeMillis());
		this.online = true;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProfile() {
		return profile;
	}
	
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public Date getLastSeen() {
		return lastSeen;
	}
	
	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}
	
	public boolean isOnline() {
		return online;
	}
	
	public void setOnline(boolean online) {
		this.online = online;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", profile='" + profile + '\'' +
				", phone='" + phone + '\'' +
				", key='" + key + '\'' +
				", timestamp=" + timestamp +
				", lastSeen=" + lastSeen +
				", online=" + online +
				'}';
	}
}
