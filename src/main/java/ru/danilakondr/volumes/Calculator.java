package ru.danilakondr.volumes;

import java.util.HashMap;
import java.math.BigDecimal;

public abstract class Calculator {
	private HashMap<String, BigDecimal> parameters;
	
	public Calculator() {
		parameters = new HashMap<>();
	}
	
	public void setParameter(String k, BigDecimal v) {
		parameters.put(k, v);
	}
	
	public BigDecimal getParameter(String k) {
		return parameters.getOrDefault(k, BigDecimal.ZERO);
	}
	
	public abstract BigDecimal calculate();
}
