package ru.danilakondr.volumes.web;

import java.math.BigDecimal;
import java.util.Map;
import ru.danilakondr.volumes.*;

public class CalculatorFactory {
	private static Map<String, Class<? extends Calculator>> calculators = Map.ofEntries(
			Map.entry("cube", CubeVolumeCalculator.class)
	);
			
	
	public static Calculator createCalc(String type, Map<String, BigDecimal> x) {
		Calculator result = null;
		
		if (!calculators.containsKey(type))
			throw new IllegalArgumentException("Calculator type not found: " + type);
		
		try {
			result = calculators.get(type).getDeclaredConstructor().newInstance();
			
			for (Map.Entry<String, BigDecimal> e : x.entrySet())
				result.setParameter(e.getKey(), e.getValue());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}

}
