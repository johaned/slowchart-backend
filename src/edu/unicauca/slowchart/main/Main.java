package edu.unicauca.slowchart.main;

import java.io.ObjectInputStream.GetField;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import edu.unicauca.slowchart.logic.FlowFactory;
import edu.unicauca.slowchart.logic.Parser;
import edu.unicauca.slowchart.miscellaneus.Log;
import edu.unicauca.slowchart.persistence.conn.MongoDBAdapter;
import edu.unicauca.slowchart.persistence.model.Flow;

public class Main {

	public static void main(String[] args) throws UnknownHostException {
		MongoDBAdapter mdbc = MongoDBAdapter.getInstance();
		DBCursor c;
		Flow f;
		// -- Erases the database
		mdbc.remove_all_doc_by_coll();
		// -- Creates test flow like a Mongo document
		BasicDBObject bdbo = new BasicDBObject();
		bdbo = FlowFactory.create_flow();
		// -- Saves test flow
		System.out.println(mdbc.insert_doc(bdbo));
		
		// -- Simulates interpreting flow process
		// - Inspects how many documents exist
		mdbc.see_all_doc_by_coll();
		// - Gets the flow objects (in this scenario only one exists)
		c = mdbc.get_cursor_by_coll();
		while (c.hasNext()) {
			f = Parser.to_convert_object((BasicDBObject) c.next());
			Log.print(f.getBranches().iterator().next().getRoute_for_true());
		}
		
	}

}
