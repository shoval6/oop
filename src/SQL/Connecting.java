package SQL;

public class Connecting {

	private String ip , port , url , user , pass , Con , tableName;
	
	public Connecting(String ip , String port , String url , String user , String pass , String con , String tablename){
		this.ip = ip;
		this.port = port;
		this.url = "jdbc:mysql://" + ip + ":" + port + "/" + tablename + "?useSSL=false";
		this.user = user;
		this.pass = pass;
		this.Con = con;
		this.tableName = tablename;
		
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCon() {
		return Con;
	}

	public void setCon(String con) {
		Con = con;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
