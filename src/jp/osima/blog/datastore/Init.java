package jp.osima.blog.datastore;

import java.sql.Connection;
import java.sql.Statement;

class Init extends Base {
	
	public Init(Param param){
		super(param);
	}

	public void process() throws Exception{
		
		Connection con = createConnection();
		Statement st = con.createStatement(); 
		String cmd = null;

		// drop
		cmd =  "drop table "+getParam().getTableName()+" if exists";
		st.execute(cmd);
		
		// create
		cmd = "create table "+getParam().getTableName()+"( key VARCHAR(36), prevkey VARCHAR(36), nextkey VARCHAR(36), value LONGVARCHAR )";
		st.execute(cmd);
		
		st.close();
		con.close();
		
		
		// insert first item
		Item item = new Item();
		item.setKey(Resources.FIRST_SYSTEM_ITEM_KEY);
		new Put(getParam()).process( item );
		
	}
	

}
