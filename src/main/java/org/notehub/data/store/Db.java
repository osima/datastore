package org.notehub.data.store;


public class Db extends Base{
	
	public Db(Param param){
		super(param);
	}
	
	public void init() throws Exception{
		new Init(getParam()).process();
	}
	public void put(Item item)  throws Exception{
		new Put(getParam()).process(item);
	}
	public Item getFirstItem() throws Exception{
		Item firstItem = new GetFirstSystemItem(getParam()).process();//Resources.FIRST_SYSTEM_ITEM_KEY);
		return new Get(getParam()).process(firstItem.getNextKey());//実際に返すのはスペシャルアイテムではない点に注意.
	}
	public Item getLastItem() throws Exception {
		return new GetLastItem(getParam()).process();
	}
	
	public Item get(String key) throws Exception{
		return new Get(getParam()).process(key);
	}

//	public ArrayList<String> getKeyList() throws Exception{
//		return new GetAllKey(getParam()).process();
//	}
	
	private class MyGetAllListener implements GetAllListener{
		
		private Param param;
		private DbListener l;
		
		public MyGetAllListener(Param param,DbListener l) {
			this.param=param;
			this.l=l;
		}
		@Override
		public void callback(Item item) {
			l.callback(item);
		}
//		@Override
//		public void callback(String itemKey) {
//			try{
//				Item item = new Get(param).process(itemKey);
//				l.callback(item);
//			}
//			catch(Exception ex){
//				ex.printStackTrace();
//			}
//		}
	}
	
	public void getAll(DbListener l) throws Exception{
		getAll(l,true);
	}
	
	public void getAll(DbListener l,boolean desc) throws Exception{
		GetAll ga =new GetAll(getParam(),desc);
		ga.addListener(new MyGetAllListener(getParam(), l));
		ga.process();
	}
	
	public void dump() throws Exception{
		new Dump(getParam()).process();
	}
}

