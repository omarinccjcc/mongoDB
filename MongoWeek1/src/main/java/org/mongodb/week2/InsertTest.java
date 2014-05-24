package org.mongodb.week2;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class InsertTest {

	public static void main(String[] args) throws UnknownHostException {
		// test1();
		// test2();
		// test3();
		// test4();
		// test5();
		// test6();
//		test7();
		test8();
	}

	public static void test8() throws UnknownHostException {
        DBCollection collection = createCollection("modifiedTest");
        collection.drop();
        
		final String counterId = "abc";
		int first;
		int numNeeded = 2;
        first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

		numNeeded = 3;
        first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

		numNeeded = 10;
        first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));


		
	}
	
    private static int getRange(String id, int range, DBCollection collection) {
        DBObject doc = collection.findAndModify(
                new BasicDBObject("_id", id), null, null, false,
                new BasicDBObject("$inc", new BasicDBObject("counter", range)),
                true, true);
        return (Integer) doc.get("counter") - range + 1;
   }
	
	//new BasicDBObject("$set",new BasicDBObject("examiner","Jones"))
	// udpate
    public static void test7() throws UnknownHostException {
        DBCollection collection = createCollection("UpdateRemoveTest");

        List<String> names = Arrays.asList("alice", "bobby", "cathy", "david", "ethan");
        for (String name : names) {
            collection.insert(new BasicDBObject("_id", name));
        }
        // see scratch method
        collection.update(new BasicDBObject("_id","alice"),new BasicDBObject("age",24));
        
		collection.update(new BasicDBObject(), 
				new BasicDBObject("$set", new  BasicDBObject ("title", "Dr.") ) ,false,true );
        		
//		collection.remove(new BasicDBObject("_id","alice"));
		
        printCollection(collection);
    }

    // these are all the statement I used throughout the lecture.
    private static void scratch(DBCollection collection) {
        collection.update(new BasicDBObject("_id", "alice"),
                new BasicDBObject("age", 24));

        collection.update(new BasicDBObject("_id", "alice"),
                new BasicDBObject("$set", new BasicDBObject("age", 24)));

        collection.update(new BasicDBObject("_id", "alice"),
                new BasicDBObject(new BasicDBObject("gender", "F")));

        collection.update(new BasicDBObject("_id", "frank"),
                new BasicDBObject("$set", new BasicDBObject("age", 24)), true, false);

        collection.update(new BasicDBObject(),
                new BasicDBObject("$set", new BasicDBObject("title", "Dr")), false, true);

        collection.remove(new BasicDBObject("_id", "frank"));
    }
	
	private static DBCollection createCollection(String table) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection(table);
		collection.drop();
		return collection;
	}

	private static void printCollection(final DBCollection collection) {
		DBCursor cursor = collection.find().sort(new BasicDBObject("_id", 1));
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

	
	
	
	
	// ordena
//	{ "_id" : 0, "value" : 10 }
//	{ "_id" : 1, "value" : 5 }
//	{ "_id" : 2, "value" : 7 }
//	{ "_id" : 3, "value" : 20 }
//	collection.find().sort(new BasicDBObject("value", -1)).skip(2).limit(1);
	public static void test6() throws UnknownHostException {

		MongoClient client = new MongoClient();
		DB course = client.getDB("course");
		DBCollection collection = course.getCollection("notNotationTest");
		collection.drop();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			
			collection.insert(new BasicDBObject("_id", i).
					append("start",
							new BasicDBObject("x", rand.nextInt(2))
									  .append("y", rand.nextInt(90) + 10)
					)
					.append("end", 
							new BasicDBObject("x", rand.nextInt(2)))
									  .append("y", rand.nextInt(90) + 10)
			);
		}
		
//		DBCursor cursor = collection.find().sort(new BasicDBObject("_id",-1)).skip(2).limit(3);
		DBCursor cursor = collection.find().sort(
				new BasicDBObject("start.x", 1).append("start.y", -1) )
				.skip(2).limit(10);
		try {
			while (cursor.hasNext()) {
				DBObject dbObject = (DBObject) cursor.next();
				System.out.println("dbObject : "+dbObject);
			}
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}
	
	public static void test5() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB course = client.getDB("course");
		DBCollection collection = course.getCollection("notNotationTest");
		collection.drop();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			
			collection.insert(new BasicDBObject("_id", i).
					append("start",
							new BasicDBObject("x", rand.nextInt(90) + 10)
									  .append("y", rand.nextInt(90) + 10)
					)
					.append("end", 
							new BasicDBObject("x", rand.nextInt(90) + 10))
									  .append("y", rand.nextInt(90) + 10)
			);
		}
		
		QueryBuilder builder = QueryBuilder.start();
		DBCursor cursor = collection.find(builder.get(), new BasicDBObject("start.y",true).append("_id", false));
		
		try {
			
			while (cursor.hasNext()) {
				DBObject dbObject = (DBObject) cursor.next();
				System.out.println("dbObject: "+dbObject);
			}
			
		} finally {
			// TODO: handle exception
			cursor.close();
		}

		
		
	}
	
	public static void test4() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB course = client.getDB("course");
		DBCollection collection = course.getCollection("findSelectionTest");
		collection.drop();

		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			collection.insert(new BasicDBObject("x", rand.nextInt(2)).append(
					"y", rand.nextInt(100)).append("z", rand.nextInt(1000)));
		}

		// DBObject query =
		// QueryBuilder.start("x").is(0).and("y").greaterThan(10)
		// .lessThan("70").get();

		DBObject query = QueryBuilder.start("x").is(0).get();

		DBCursor cursor = collection.find(query, new BasicDBObject("y", true)
				.append("_id", false));
		System.out.println("cursor: " + cursor);
		try {
			while (cursor.hasNext()) {
				DBObject dbObject = (DBObject) cursor.next();
				System.out.println("dbObject: " + dbObject);
			}
		} finally {
			// TODO: handle exception
		}

	}

	public static void test3() throws UnknownHostException {

		MongoClient client = new MongoClient();
		DB course = client.getDB("course");
		DBCollection collection = course.getCollection("findCriteriaTest");
		collection.drop();
		for (int i = 0; i < 10; i++) {
			collection.insert(new BasicDBObject("x", new Random().nextInt(2))
					.append("y", new Random().nextInt(100)));
		}

		System.out.println("Find One");
		DBObject one = collection.findOne();
		System.out.println("Find One: " + one);

		DBCursor curso = collection.find();
		// try {
		// System.out.println("Find All: " + curso);
		// int count = 0;
		// while (curso.hasNext()) {
		// count++;
		// DBObject dbObject = (DBObject) curso.next();
		// System.out.println(count + " :: " + dbObject);
		// }
		// } finally {
		// curso.close();
		// }

		QueryBuilder builder = QueryBuilder.start("x").is("0").and("y")
				.greaterThan("10").lessThan(70);

		DBObject query = new BasicDBObject("x", 0).append("y",
				new BasicDBObject("$gt", "100").append("$lt", "90"));

		System.out.println("Find Count");
		// long count2 = collection.count(query);
		long count2 = collection.count(builder.get());
		System.out.println("Find Count::::: " + count2);

	}

	public static void test2() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB course = client.getDB("course");
		DBCollection collection = course.getCollection("findTest");
		collection.drop();
		for (int i = 0; i < 10; i++) {
			collection
					.insert(new BasicDBObject("x", new Random().nextInt(100)));
		}
		System.out.println("Find One");
		DBObject one = collection.findOne();
		System.out.println("Find One: " + one);

		System.out.println("Find All");
		DBCursor curso = collection.find();
		try {
			System.out.println("Find All: " + curso);
			int count = 0;
			while (curso.hasNext()) {
				count++;
				DBObject dbObject = (DBObject) curso.next();
				System.out.println(count + " :: " + dbObject);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			curso.close();
		}

		System.out.println("Find Count");
		long count2 = collection.count();
		System.out.println("Find Count: " + count2);

	}

	public static void test1() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB course = client.getDB("course");
		DBCollection collection = course.getCollection("insertTest");

		collection.drop();

		DBObject doc = new BasicDBObject("_id", new ObjectId()).append("x", 1);
		// DBObject doc1 = new BasicDBObject().append("x", 2); //
		System.out.println("doc " + doc);
		// collection.insert( Arrays.asList(doc,doc1) );
		// System.out.println("doc " + doc);
		collection.insert(doc);
		// collection.insert( doc);
	}

}
