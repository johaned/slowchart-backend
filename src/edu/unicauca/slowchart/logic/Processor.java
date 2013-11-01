package edu.unicauca.slowchart.logic;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import edu.unicauca.slowchart.miscellaneus.Log;
import edu.unicauca.slowchart.persistence.model.Branch;
import edu.unicauca.slowchart.persistence.model.Decision;
import edu.unicauca.slowchart.persistence.model.Flow;
import edu.unicauca.slowchart.persistence.model.Operation;

public class Processor {
	private CommunicationAdapter ca;
	private ScriptEngine engine;
	private static boolean NOALERT = false;
	private static boolean ALERT = true;
	private static int BRANCHMAX = 100;
	private static int OPEARTIONMAX = 100;

	public Processor(CommunicationAdapter ca) {
		super();
		this.ca = ca;
		ScriptEngineManager manager = new ScriptEngineManager();
		this.engine = manager.getEngineByName("js");
	}

	public boolean process(Flow flow) {
		Branch branch = new Branch();
		Iterator<Branch> branches_iterator = flow.getBranches().iterator();
		int branchesIterations = 0;

		while (branches_iterator.hasNext()) {
			branch = branches_iterator.next();
			if (branch.is_root())
				break;
			//*******************************
		}

		while (true) {
			String response = process_branch(branch);
			if (response.equals("alert")) {
				return ALERT;
			} else if (response.equals("noalert")) {
				return NOALERT;
			} else if (branchesIterations > BRANCHMAX) {
				return NOALERT;
			} else if (isNumeric(response)) {
				branch = flow.get_branch_by_id(Long.parseLong(response));
				if (branch == null) {
					return NOALERT;
				}
			} else {
				return NOALERT;
			}
			branchesIterations++;
		}
	}

	private String process_branch(Branch branch) {
		Iterator<Operation> operations_iterator = branch.getOperations().iterator();
		Operation operation = new Operation();
		Double operationResult = 0.0;
		int operationsIterations = 0;
		String nextNode;

		if (branch.getOperations().size() > 0) {
			while (operations_iterator.hasNext()) {
				operation = operations_iterator.next();
				if (operation.is_root())
					break;
				//*******************************
			}

			while (true) {
				operationResult = process_operation(operation, operationResult);
				nextNode = operation.getNext_node();
				if (isNumeric(nextNode)) {
					operation = branch.get_operation_by_id(Long.parseLong(operation.getNext_node()));
				} else {
					break;
				}
				if (operation == null)
					break;
				operationsIterations++;
				if (operationsIterations > OPEARTIONMAX) {
					break;
				}
			}
		}
		if (process_decision(branch.getDecision(), operationResult)){
			return branch.getRoute_for_true();
		}else{
			return branch.getRoute_for_false();
		}
	}

	private Double process_operation(Operation operation, Double transientVariable) {
		if (transientVariable == null){
			transientVariable = 0.0;
		}
		Double result = 0.0;
		String expression = operation.getProcess();
		String id;
		String token;
		// The Regular expression (Finds {id} tokens)
	    Pattern pt = Pattern.compile("\\{([^}]*)\\}");
	    // Match the string with the pattern
	    Matcher m = pt.matcher(expression);
	    
	    while(m.find()){
	        id = m.group(1); // ID (Get without {})
	        token = m.group(0); // Expression with {}
	        if(id.equals("transient_variable")){
	        	expression = expression.replace(token, transientVariable.toString());
	        }else{
	        	expression = expression.replace(token, ca.get_attribute(id).toString());
	        }
	    }
		try {
			Log.print(expression);
			result = Double.parseDouble(engine.eval(expression).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean process_decision(Decision decision, Double operationResult) {
		if (operationResult == null){
			operationResult = 0.0;
		}
		boolean result = false;
		String expression = decision.getProcess();
		String id;
		String token;
		// The Regular expression (Finds {id} tokens)
	    Pattern pt = Pattern.compile("\\{([^}]*)\\}");
	    // Match the string with the pattern
	    Matcher m = pt.matcher(expression);
	    
	    while(m.find()){
	        id = m.group(1); // ID (Get without {})
	        token = m.group(0); // Expression with {}
	        if(id.equals("operation_result")){
	        	expression = expression.replace(token, operationResult.toString());
	        }else{
	        	expression = expression.replace(token, ca.get_attribute(id).toString());
	        }
	    }
		try {
			Log.print(expression);
			result = (Boolean) engine.eval(expression);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
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
