
import jp.osima.blog.datastore.*
import groovy.util.GroovyTestCase


class Test extends GroovyTestCase {


	Db createDb(){
		Param param=new Param();
		Db db = new Db(param);
	}


	//def server 
	void setUp(){
		def dbName = 'mydb'
		def dbPath = "file:${dbName}"
		def myargs = ['--database.0',dbPath,'--dbname.0',dbName] as String[]
		org.hsqldb.server.Server.main( myargs )


		// init db
		def db=createDb()
		db.init();
	}

	void tearDown(){
		// shutdown db...
	}

	void test1(){

		def createItem = { String value->
			Item item=new Item()
			item.setValue(value)
			item
		}

		def db=createDb()

		Item item = createItem("hello world-1")
		db.put(item);

		db.put(createItem("hello world-2"))
		db.put(createItem("hello world-3"))
		db.put(createItem("hello world-4"))
		db.put(createItem("hello world-5"))
		
		// update
		item.setValue(item.getValue()+"-hogehoge")
		db.put(item);
			
		db.dump();
	}

	void test2(){

		def db=createDb()

		println '---'
		db.getAll( { println it } as DbListener,true )

		println '---'
		db.getAll( { println it } as DbListener,false )
			
	}
}	

