package org.mongodb.week2;

import org.bson.BasicBSONObject;

import com.mongodb.DBObject;

public class BasicDBObject_ extends BasicBSONObject implements DBObject {

	private static final long serialVersion = -21313123123L;

	public BasicDBObject_() {
	}

	public BasicDBObject_(int size) {
		super(size);
	}

	public boolean isPartialObject() {
		// TODO Auto-generated method stub
		return false;
	}

	public void markAsPartialObject() {
		// TODO Auto-generated method stub

	}

}
