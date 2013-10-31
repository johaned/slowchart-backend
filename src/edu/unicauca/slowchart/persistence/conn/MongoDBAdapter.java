package edu.unicauca.slowchart.persistence.conn;

import java.net.UnknownHostException;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongoDBAdapter {
	private static MongoDBAdapter instance;
	private MongoClient mc;
	private DB db;
	private DBCollection coll;
	
	private MongoDBAdapter() throws UnknownHostException{
		mc = new MongoClient();
		db = mc.getDB("slowchart");
		coll = db.getCollection("flow");
		//coll.createIndex(new BasicDBObject("index_test", 1));
	}
	
	public static MongoDBAdapter getInstance() throws UnknownHostException{
		if(instance==null){
			instance = new MongoDBAdapter();
		}
		return instance;
	}
	
	public WriteResult insert_doc(DBObject dbo){
		return coll.insert(dbo);
	}
	
	public DBCursor get_cursor_by_coll(){
		return coll.find();
	}
	
	public void see_all_doc_by_coll(){
		DBCursor c = get_cursor_by_coll();
		try {
			while (c.hasNext()) {
				System.out.println(c.next());
			}
		} finally {
			c.close();
		}
	}
	public void remove_all_doc_by_coll(){
		DBCursor c = get_cursor_by_coll();
		try {
			while (c.hasNext()) {
				coll.remove(c.next());
			}
		} finally {
			c.close();
		}
	}
	@SuppressWarnings("finally")
	public BasicDBObject get_document_by_key(String key, Object value){
		DBCursor cursor = coll.find(new BasicDBObject(key,value));
		BasicDBObject dbo = new BasicDBObject();
        try {
            while(cursor.hasNext()) {
            	dbo = (BasicDBObject) cursor.next();
                System.out.println(dbo);
                
            }
        } finally {
            cursor.close();
            return dbo;
        }
	}

	public void do_test_aggregation() {
		DBObject fields = new BasicDBObject("id",1);
		//fields.put("lyr", 1);
		DBObject projection = new BasicDBObject("$project",fields);
		DBObject groupFields = new BasicDBObject( "_id", "maximo");
		groupFields.put("max", new BasicDBObject( "$max", "$id"));
		DBObject group = new BasicDBObject("$group", groupFields);
		AggregationOutput output = coll.aggregate( projection, group );
		System.out.println(output.getCommandResult());
	}

}
