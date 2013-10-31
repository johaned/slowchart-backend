package edu.unicauca.slowchart.persistence.model;

import java.util.Iterator;
import java.util.List;
/**
 * Branch.java
 * Purpose: Encapsulates the Branch document in a Java object.
 * Description: Branch abstracts the operations set and decision block, Branch entity knows
 * who are your children and your parent, also these can point to root and end node into 
 * main the flow   
 * @author Johan Tique
 * @version 1.0 10/10/13
 */
public class Branch {
	private Long id;
	private String parent;
	private List<Operation> operations;
	private Decision decision;
	private String route_for_true;
	private String route_for_false;
	
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Branch(Long id, String parent, List<Operation> operations,
			Decision decision, String route_for_true, String route_for_false) {
		super();
		this.id = id;
		this.parent = parent;
		this.operations = operations;
		this.decision = decision;
		this.route_for_true = route_for_true;
		this.route_for_false = route_for_false;
	}
	
	public boolean is_root(){
		if(this.parent=="root")
			return true;
		else
			return false;
	}
	
	public Operation get_operation_by_id(Long id){
		Operation o;
		Iterator<Operation> i = this.operations.iterator();
		while(i.hasNext()){
			o = i.next();
			if(o.getId() == id){
				return o;
			}
		}
		return null;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	public Decision getDecision() {
		return decision;
	}
	public void setDecision(Decision decision) {
		this.decision = decision;
	}
	public String getRoute_for_true() {
		return route_for_true;
	}
	public void setRoute_for_true(String route_for_true) {
		this.route_for_true = route_for_true;
	}
	public String getRoute_for_false() {
		return route_for_false;
	}
	public void setRoute_for_false(String route_for_false) {
		this.route_for_false = route_for_false;
	}
	
	
}
