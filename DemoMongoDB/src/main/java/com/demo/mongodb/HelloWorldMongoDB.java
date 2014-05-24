package com.demo.mongodb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class HelloWorldMongoDB {

	public static void main(String[] arg) throws UnknownHostException {
		
		MongoClient mongoClient =  new MongoClient(new ServerAddress("localhost",27017));
		DB database = mongoClient.getDB("test");
		
		DBCollection tableMyCollection = database.getCollection("mycollection");
		WriteConcern concern  = new WriteConcern();
		
		// find information
		DBObject documentFind = tableMyCollection.findOne();
//		System.out.println("document: "+documentFind);
		
//		insert information
		BasicDBObject document = new BasicDBObject();
		document.put("example", "example");
//		tableMyCollection.insert(document, concern);
		
//		Find and display information
		DBCursor cursor = tableMyCollection.find(document);
		
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		
		
		
//		update information
//		Search document where test="working"
		System.out.println("=========================");
		document = new BasicDBObject();
		document.put("test", "working");
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("test", "mkyong-updated");
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
		
		tableMyCollection.update(document,updateObj);
		
		/**** Find and display ****/
		BasicDBObject searchQuery2 
		    = new BasicDBObject().append("test", "mkyong-updated");
	 
		DBCursor cursor2 = tableMyCollection.find(searchQuery2);
	 
		while (cursor2.hasNext()) {
			System.out.println(cursor2.next());
		}
	}
	
	
}
