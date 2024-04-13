package ru.danilakondr.volumes.web;

import java.math.BigDecimal;
import java.util.Map;
import ru.danilakondr.volumes.*;

public class CalculatorFactory {
	
	public static Calculator createCalc(String type, Map<String, BigDecimal> x) {
		Calculator result = null;
		
		switch (type) {
		case "cube":
			result = new CubeVolumeCalculator();
			break;
		}
		
		for (Map.Entry<String, BigDecimal> e : x.entrySet())
			result.setParameter(e.getKey(), e.getValue());
		
		return result;
	}

}
