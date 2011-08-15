package jp.osima.blog.datastore;

public class Param {
	
	private String hostname;
	private String dbname; 
	private String password;
	private String tableName;
	
	
	public Param(){
		super();
		
		hostname="localhost";
		dbname="mydb";
		password="";

		tableName = "mytbl";
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
