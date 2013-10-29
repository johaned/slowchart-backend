package edu.unicauca.slowchart.persistence.model;

import java.util.List;
/**
 * Operation.java
 * Purpose: Encapsulates the Operation document in a Java object.
 * Description: Operation abstracts a processes chained set that represent a calculate process between
 * attributes and another data set by the user in the GUI  
 * @author Johan Tique
 * @version 1.0 10/10/13
 */
public class Operation {
	private String name;
	private Long id;
	private List<String> process;
	private String previous_node;
	private String next_node;
	
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Operation(String name, Long id, List<String> process,
			String previous_node, String next_node) {
		super();
		this.name = name;
		this.id = id;
		this.process = process;
		this.previous_node = previous_node;
		this.next_node = next_node;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<String> getProcess() {
		return process;
	}
	public void setProcess(List<String> process) {
		this.process = process;
	}
	public String getPrevious_node() {
		return previous_node;
	}
	public void setPrevious_node(String previous_node) {
		this.previous_node = previous_node;
	}
	public String getNext_node() {
		return next_node;
	}
	public void setNext_node(String next_node) {
		this.next_node = next_node;
	}

	
}
