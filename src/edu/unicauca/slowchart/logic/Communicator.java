package edu.unicauca.slowchart.logic;

import java.util.HashMap;

public class Communicator implements CommunicationAdapter {

	HashMap<String, String> fictitiousAttributes;
	
	public Communicator() {
		super();
		fictitiousAttributes = AttributeValuesFactory.create_fictitious_attr_values();
	}
	
	@Override
	public Object get_attribute(String id) {
		String value = fictitiousAttributes.get(id);
		if(isNumeric(value)){
			return value;
		}else{
			return "'"+value+"'";
		}	
	}
	
	private static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	

}
