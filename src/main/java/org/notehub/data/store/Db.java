package org.notehub.data.store;


public class Db {// extends Base{

	private Param param;
	private Param getParam(){ return param; }
	public Db(Param param){
		this.param=param;
	}
	
	//public Db(Param param){ super(param); }
	
	public void init() throws Exception{
		new Init(getParam()).process();
	}
	public void put(Item item)  throws Exception{
		new Put(getParam()).process(item);
	}
	public Item getFirstItem() throws Exception{
		Item firstItem = new GetFirstSystemItem(getParam()).process();
		return new Get(getParam()).process(firstItem.getNextKey());
	}
	public Item getLastItem() throws Exception {
		return new GetLastItem(getParam()).process();
	}
	
	public Item get(String key) throws Exception{
		return new Get(getParam()).process(key);
	}

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
	}
	
	public void getAll(DbListener l) throws Exception{
		getAll(l,false);
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

