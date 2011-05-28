package org.notehub.data.store;

import java.sql.ResultSet;

class ItemBuildClosure {
	
	Item call( ResultSet rs ) throws Exception{
		Item item = new Item();
		item.setValue(rs.getString("value"));
		item.setKey(rs.getString("key"));
		item.setNextKey(rs.getString("nextkey"));
		item.setPrevKey(rs.getString("prevkey"));
		return item;
	}

}
