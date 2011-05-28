package org.notehub.data.store;

import java.sql.Connection;
import java.sql.DriverManager;

abstract class Base {

	private Param param;
	protected Param getParam(){ return param; }
	public Base(Param param){
		this.param=param;
	}
	protected Connection createConnection() throws Exception {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://"+param.getHostname()+"/"+param.getDbname(), "sa", param.getPassword());
		return con;
	}
}
