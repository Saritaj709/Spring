package com.drivers.annotations;

public class OracleDriver implements DataBaseDriver {

	protected String url;
	protected String user;
	protected String password;
	protected String driver;
	protected Integer port;

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "[Driver: Oracle" + " ,url:" + url + " ,user:" + user + " ,password:" + password + " ,driver:" + driver
				+ " ,port:" + port + "]";
	}

}
