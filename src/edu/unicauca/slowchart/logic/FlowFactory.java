package edu.unicauca.slowchart.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class FlowFactory {
	
	public static BasicDBObject create_flow() {
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.put("macro_attribute_id", "1e456ef8456a46b6c0");
		bdbo.put("branches", create_branches_group());
		return bdbo;	
	}
	
	private static BasicDBList create_branches_group(){
		BasicDBList bdbl = new BasicDBList();	
		List<HashMap<String,String>> branches = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> branch_container;
		// in this scenario each branch will have just one operation
		// first branch
		branch_container = new HashMap<String, String>();
		branch_container.put("parent", "root");
		branch_container.put("id", "1");
		branch_container.put("operation", "");
		branch_container.put("decision", "({1e456ef8456a46b6c6} == 'MPEG4'");
		branch_container.put("route_for_true", "2");
		branch_container.put("route_for_false", "");
		branches.add(branch_container);
		// second branch
		branch_container = new HashMap<String, String>();
		branch_container.put("parent", "root");
		branch_container.put("id", "2");
		branch_container.put("operation", "((7+{1e456ef8456a46b6c1})/15)+8*{1e456ef8456a46b6c2}-10*{1e456ef8456a46b6c3}");
		branch_container.put("decision", "({operation_result}>5 && {operation_result}<10");
		branch_container.put("route_for_true", "");
		branch_container.put("route_for_false", "3");
		branches.add(branch_container);
		// third branch
		branch_container = new HashMap<String, String>();
		branch_container.put("parent", "root");
		branch_container.put("id", "3");
		branch_container.put("operation", "5*{1e456ef8456a46b6c4}-2*{1e456ef8456a46b6c5}");
		branch_container.put("decision", "({operation_result}>10 && {operation_result}<20");
		branch_container.put("route_for_true", "end");
		branch_container.put("route_for_false", "");
		branches.add(branch_container);
		
		// iterate the list (creating the branches)
		Iterator<HashMap<String, String>> i = branches.iterator();
		while(i.hasNext()){
			branch_container = i.next();
			bdbl.add(create_branch(Long.parseLong(branch_container.get("id")), branch_container.get("parent"), branch_container.get("operation"), branch_container.get("decision"), branch_container.get("route_for_true"), branch_container.get("route_for_false")));
		}
		
		return bdbl;
	}
	
	private static BasicDBObject create_branch(long id, String parent, String operation, String decision, String route_for_true, String route_for_false){
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.put("id", id);
		if (parent!=null) bdbo.put("parent", parent);
		bdbo.put("operations", create_operations_group(operation));
		bdbo.put("decision", create_decision("decision_"+id, decision));
		bdbo.put("route_for_true", route_for_true);
		bdbo.put("route_for_false", route_for_false);
		return bdbo;
	}
	
	private static BasicDBList create_operations_group(String operation) {
		BasicDBList bdbl = new BasicDBList();
		// it creates just one operation
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.put("id", 1);
		bdbo.put("name", "My operation");
		bdbo.put("process", operation);
		bdbo.put("previous_node", "root");
		bdbo.put("next_node", "end");
		bdbl.add(bdbo);
		return bdbl;
	}
	
	private static BasicDBObject create_decision(String name, String process){
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.put("name", name);
		bdbo.put("process", process);
		return bdbo;	
	}
}
