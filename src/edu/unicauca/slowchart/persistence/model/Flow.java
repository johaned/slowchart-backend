package edu.unicauca.slowchart.persistence.model;

import java.util.List;
/**
 * Flow.java
 * Purpose: Encapsulates the Flow document in a Java object.
 * Description: Flow represents a flow that describes a specific flow chart, therefore a Flow points to GESTV MacroAttribute and
 * this is composite by several branches, each branch represents a unitary flow that contains operations and one decision, this decision
 * will have two outputs, route for true and route for false 
 * @author Johan Tique
 * @version 1.0 10/10/13
 */
public class Flow {
	private _Id _id;
	private String macro_attribute_id;
	private List<Branch> branches;
	
	public Flow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Flow(_Id _id, String macro_attribute_id, List<Branch> branches) {
		super();
		this._id = _id;
		this.macro_attribute_id = macro_attribute_id;
		this.branches = branches;
	}
	public _Id get_id() {
		return _id;
	}
	public void set_id(_Id _id) {
		this._id = _id;
	}
	public String getMacro_attribute_id() {
		return macro_attribute_id;
	}
	public void setMacro_attribute_id(String macro_attribute_id) {
		this.macro_attribute_id = macro_attribute_id;
	}
	public List<Branch> getBranches() {
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	
}
