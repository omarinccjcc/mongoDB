package org.mongodb.week2;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class DocumentRepresentation {

	public static void main(String[] args) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("userName", "ocalsin");
		doc.put("birtDate", new Date());
		doc.put("programmer", true);
		doc.put("age", 28);
		doc.put("language", Arrays.asList("Java", "C++"));
		doc.put("address", new BasicDBObject("Street", "20 Main").append(
				"town", "Westfild").append("zip", "12432"));

	}

}
