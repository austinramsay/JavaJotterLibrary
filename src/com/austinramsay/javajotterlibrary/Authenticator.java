package com.austinramsay.javajotterlibrary;

import java.io.Serializable;

public class Authenticator implements Serializable {

	private boolean authenticated;
	private boolean hasExistingConnection;
	private String username;
	private String password;
	private String serverAddress;

	public Authenticator(String username, char[] password, String serverAddress) {
		this.username = username;
		this.password = new String(password);
		this.serverAddress = serverAddress;
		this.authenticated = false;
		this.hasExistingConnection = false;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public boolean hasExistingConnection() {
		return hasExistingConnection;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void setHasExistingConnection(boolean hasExistingConnection) {
		this.hasExistingConnection = hasExistingConnection;
	}

	public String getUsername () {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void stripCredentials() {
		password = null;
	}
}
