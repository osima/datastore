package jp.osima.blog.datastore;


/**
 * 最初に追加された Item を取得する.
 */
class GetFirstSystemItem extends Base{
	
	public GetFirstSystemItem(Param param){
		super(param);
	}
	
	class MyGetFirstSystemItem  extends AbstractGet{
		public MyGetFirstSystemItem(Param param) {
			super(param);
		}
		Item process() throws Exception {
			return super.process(Resources.FIRST_SYSTEM_ITEM_KEY);
		}
	}
	
	public Item process() throws Exception {
		return new MyGetFirstSystemItem(getParam()).process(Resources.FIRST_SYSTEM_ITEM_KEY);
	}
}
