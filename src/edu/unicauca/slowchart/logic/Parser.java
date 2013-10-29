package edu.unicauca.slowchart.logic;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;

import edu.unicauca.slowchart.persistence.model.Flow;

public class Parser {
	public static Flow to_convert_object(
			BasicDBObject dbo) {
		Flow f = new Gson().fromJson(dbo.toString(), Flow.class);
		System.out.println("ID MR: "+f.get_id());
		return f;

	}
}
