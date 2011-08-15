package jp.osima.blog.datastore;

import java.util.UUID;

public class Item {
	
	private String key;
	private String nextKey;
	private String prevKey;
	
	private String value;
	
	/** スペシャルアイテムかどうか */
	protected boolean isFirstSystemItem(){ 
		return (getKey().equals(Resources.FIRST_SYSTEM_ITEM_KEY));
	}
	

	
	public String getKey() {
		if(key==null)
			key=UUID.randomUUID().toString();
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getNextKey() {
		return nextKey;
	}
	protected void setNextKey(String nextKey) {
		this.nextKey = nextKey;
	}
	
	public String getPrevKey() {
		return prevKey;
	}
	protected void setPrevKey(String prevKey) {
		this.prevKey = prevKey;
	}


	
	public String getValue() {
		if(value==null)
			value="";
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		return getKey()+":"+getValue();
	}

}
