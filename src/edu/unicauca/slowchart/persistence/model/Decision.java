package edu.unicauca.slowchart.persistence.model;

import java.util.List;
/**
 * Decision.java
 * Purpose: Encapsulates the Decision document in a Java object.
 * Description: Decision abstracts a decision processes chained set that represent a
 * conditional process between the operations results, another data set by the user 
 * in the GUI and attributes belongs to macro attribute associated  
 * @author Johan Tique
 * @version 1.0 10/10/13
 */
public class Decision {
	private String name;
	private String process;
	
	public Decision() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Decision(String name, String process) {
		super();
		this.name = name;
		this.process = process;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	
}
