package org.notehub.data.store;

import java.sql.Connection;
import java.sql.PreparedStatement;

class Put extends Base{
	
	public Put(Param param){
		super(param);
	}
	
//	private String getKeyThatIsNextKeyIs0(Connection con) throws Exception{
//		String key = null;
//		
//		Statement st = con.createStatement(); 
//		
//		// nextkey 値が 0 の row.key (つまりそれが最後に追加された row なのだが...) を取得.
//		String cmd = "select key from "+getParam().getTableName()+" where nextkey='0'";
//		ResultSet rs = st.executeQuery(cmd);
//		if( rs.next() ){
//			key = rs.getString("key");
//		}
//		rs.close();
//		st.close();
//		
//		return key;
//	}
	
	public void process(Item item)  throws Exception{
		process(item.isFirstSystemItem(),item.getKey(),item.getValue());
	}
	synchronized private void process(boolean first,String key,String value) throws Exception{
		
		if( first ){
			// 最初にデータベースに入れるスペシャルアイテムは、値がすべて 0 
			// ただし、nextkey の値はあとで書きかえられて 0 以外の普通の uuid 値になる.
			
			Connection con = createConnection();
			
			String cmd = "insert into "+getParam().getTableName()+" (key,nextkey, value) values(?,?,?)";

			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, Resources.FIRST_SYSTEM_ITEM_KEY);
			ps.setString(2, "0");
			ps.setString(3, "0");
			ps.execute();
			ps.close();
			
			con.close();
		}
		else{
			//
			if( new Get(getParam()).process(key)==null ){
				//存在していない場合 == 新規追加
				insert(key,value);
			}
			else{
				//既存のケース
				update(key,value);
			}
		}
	}
	
	private void update(String key,String value) throws Exception {
		
		//
		// when update, just update value.
		//
		
		Connection con = createConnection();
		
		String cmd = "update "+getParam().getTableName()+" set value=? where key=?";
		
		PreparedStatement ps=con.prepareStatement(cmd);
		ps.setString(1, value);
		ps.setString(2, key);
		ps.execute();
		ps.close();

		con.close();
	}
	
	private void insert(String key,String value) throws Exception {
		
		
		Item prevItem = new GetLastItem(getParam()).process();
		
		Connection con = createConnection();
		
		
		//String keyOfPrevRow = getKeyThatIsNextKeyIs0(con);
		if( prevItem!=null ){
			
			// 1)
			// update prevItem's the next-key field
			
			String cmd = "update "+getParam().getTableName()+" set nextkey=? where key=?";
			
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, key);
			ps.setString(2, prevItem.getKey());
			ps.execute();
			ps.close();
			
		}
		if( prevItem!=null ){
			
			// 2)
			// insert new Item
			
			
			String cmd = "insert into "+getParam().getTableName()+" (key,prevkey,nextkey,value) values(?,?,?,?)";

			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, key);
			ps.setString(2, prevItem.getKey());
			ps.setString(3, "0");
			ps.setString(4, value);
			ps.execute();
			ps.close();
		}
		
		con.close();
	
	}
}
