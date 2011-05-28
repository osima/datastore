package org.notehub.data.store;

import java.util.ArrayList;

class GetAll extends Base{
	
	private boolean desc;
	public GetAll(Param param,boolean desc){
		super(param);
		this.desc=desc;
	}
	
	private ArrayList<GetAllListener> listenerList = new ArrayList<GetAllListener>(); 
	public void addListener(GetAllListener l){
		listenerList.add(l);
	}
	
//	private void callback(String itemKey){
//		for( GetAllListener l : listenerList ){
//			l.callback(itemKey);
//		}
//	}
	private void callback(Item item){
		for( GetAllListener l : listenerList ){
			l.callback(item);
		}
	}
	
	public void process() throws Exception {
		
		if(desc){
			Item item = new GetLastItem(getParam()).process();
			while(item!=null){
				if(item.isFirstSystemItem()==false)
					callback(item);

				item = new Get(getParam()).process(item.getPrevKey());
			}
		}
		else{
			Item item = new GetFirstSystemItem(getParam()).process();
			while(item!=null){
				if(item.isFirstSystemItem()==false)
					callback(item);

				item = new Get(getParam()).process(item.getNextKey());
			}
		}		

	}
}
