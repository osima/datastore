package jp.osima.blog.datastore;


class Get extends AbstractGet {
	
	public Get(Param param){
		super(param);
	}
	
	public Item process(String key) throws Exception {
		
		// スペシャルアイテムと同じキーがリクエストされた場合は null を返す.
		if( key.equals(Resources.FIRST_SYSTEM_ITEM_KEY) ){
			return null;
		}
		return super.process(key);
	}

}
