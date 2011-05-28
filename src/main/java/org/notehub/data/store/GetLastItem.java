package org.notehub.data.store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class GetLastItem extends Base{
	
	public GetLastItem(Param param){
		super(param);
	}
	
	public Item process() throws Exception {
		
		Connection con = createConnection();
		Statement st = con.createStatement(); 
		
		// nextkey 値が 0 の row.key (つまりそれが最後に追加された row なのだが...) を取得.
		String cmd = "select * from "+getParam().getTableName()+" where nextkey='0'";
		
		ResultSet rs = st.executeQuery(cmd);
		
		List<Item> rlist = new ArrayList<Item>();
		while ( rs.next() ) {
			rlist.add( new ItemBuildClosure().call(rs) ); 
		}
		rs.close();
		st.close();
		con.close();

		if( rlist.size()>0 ){
			return rlist.get(0);
		}
		
		return null;
	}
}
