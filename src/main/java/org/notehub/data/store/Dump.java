package org.notehub.data.store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Dump extends Base{
	
	public Dump(Param param){
		super(param);
	}
	
	public void process() throws Exception {
		
		Connection con = createConnection();
		Statement st = con.createStatement(); 

		String cmd = "select * from "+getParam().getTableName();
		
		ResultSet rs = st.executeQuery(cmd);
		
		List<Item> rlist = new ArrayList<Item>();
		while ( rs.next() ) {
			rlist.add( new ItemBuildClosure().call(rs) ); 
		}
		
		for(Item item: rlist){
			System.out.println( item );
		}
		
		rs.close();
		st.close();
		con.close();

	}
}
