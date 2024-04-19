package ru.danilakondr.volumes.web;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import ru.danilakondr.volumes.*;

/**
 * Класс-фабрика калькуляторов. Несмотря на то, что все калькуляторы
 * публично доступны (они содержатся в пакете <code>ru.danilakondr.volumes</code>),
 * было бы крайне неудобно использовать switch-case для того, чтобы создавать
 * объекты калькуляторов по их идентификаторам.
 * <p>
 * Благодаря рефлексии, которая используется в Java, можно сопоставить
 * каждому названию свой класс, который можно создать при помощи получения
 * и вызова соответствующего конструктора.
 * 
 * @author Данила А. Кондратенко
 * @since 0.1.0
 */
public class CalculatorFactory {
	private static Map<String, Class<? extends Calculator>> calculators = Map.ofEntries(
			Map.entry("cube", CubeVolumeCalculator.class)
			, Map.entry("box", BoxVolumeCalculator.class)
			, Map.entry("ball", BallVolumeCalculator.class)
			, Map.entry("pyramid", PyramidVolumeCalculator.class)
			, Map.entry("strprism", PrismVolumeCalculator.class)
		);
		
	
	public static String[] getCalcTypes()
	{
		String[] result = new String[calculators.size()];
		calculators.keySet().toArray(result);
		
		return result;
	}
	
	private static Object getField(String type, String field, Object defObj)
	{
		try {
			Class<? extends Calculator> c = calculators.get(type);
			if (c == null)
				return defObj;
			
			Field f = c.getDeclaredField(field);
			f.setAccessible(true);
			if (Modifier.isStatic(f.getModifiers())) {
				return f.get(null);
			}
			else {
				return defObj;
			}
		}
		catch (NoSuchFieldException e) {
			return defObj;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getCalcName(String type)
	{
		return (String) getField(type, "CALC_NAME", "Неизвестный калькулятор");
	}
	
	public static Calculator createCalc(String type, int precision, Map<String, BigDecimal> x) {
		Calculator result = null;
		
		if (!calculators.containsKey(type))
			throw new IllegalArgumentException("Calculator type not found: " + type);
		
		try {
			result = calculators.get(type).getDeclaredConstructor().newInstance();
			if (precision != 0)
				result.setPrecision(precision);
			
			for (Map.Entry<String, BigDecimal> e : x.entrySet())
				result.setParameter(e.getKey(), e.getValue());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public static Calculator createCalc(Map<String, String> x) {
		if (!x.containsKey("type"))
			throw new IllegalArgumentException("type key not found in the parameter map");
		
		HashMap<String, BigDecimal> parameters = new HashMap<>();
		String type = "";
		int precision = 0;
		
		for (Map.Entry<String, String> e : x.entrySet()) {
			if (e.getKey().compareTo("type") == 0) {
				type = e.getValue();
				continue;
			}
			if (e.getKey().compareTo("precision") == 0) {
				try {
					precision = Integer.parseInt(e.getValue());
				}
				catch (NumberFormatException passed) {
					precision = 0;
				}
				continue;
			}
			
			BigDecimal val = new BigDecimal(e.getValue());
			parameters.put(e.getKey(), val);
		}
		
		return createCalc(type, precision, parameters);
	}

}
