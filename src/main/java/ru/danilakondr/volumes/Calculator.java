package ru.danilakondr.volumes;

import java.util.HashMap;
import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Абстрактный класс, отвечающий за вычисление какого-либо
 * математического выражения. Работает с объектами типа
 * BigDecimal.
 * <p>
 * Хранит параметры в хэш-таблице, каждый параметр имеет
 * строковое название.
 * 
 * @author Данила А. Кондратенко
 * @since 0.1.0
 */
public abstract class Calculator {
	private static final String[] _EMPTY_PARAM_LIST = new String[0];
	private HashMap<String, BigDecimal> parameters;
	
	public Calculator() {
		parameters = new HashMap<>();
	}

	/**
	 * Установить значение параметра.
	 * 
	 * @param name название параметра
	 * @param value значение параметра
	 */
	public void setParameter(String name, BigDecimal value) {
		parameters.put(name, value);
	}
	
	/**
	 * Получить значение параметра.
	 * 
	 * @param name название параметра
	 * @return значение параметра, по умолчанию &mdash; <code>BigDecimal.ZERO</code>
	 */
	public BigDecimal getParameter(String name) {
		return parameters.getOrDefault(name, BigDecimal.ZERO);
	}

	private Object getField(String name, Object defObj)
	{
		Class<? extends Calculator> c = this.getClass();
		
		try {
			Field field = c.getDeclaredField(name);
			field.setAccessible(true);
			Object obj = field.get(this);
			
			return obj;
		}
		catch (NoSuchFieldException e) {
			return defObj;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @return список параметров
	 */
	public String[] getParameters()
	{
		return (String[]) getField("PARAMETERS", _EMPTY_PARAM_LIST);
	}
	
	/**
	 * @return название калькулятора
	 */
	public String getName()
	{
		return (String) getField("CALC_NAME", "Неизвестный калькулятор");
	}
	
	/**
	 * @return документация в формате HTML
	 */
	public String getDocumentation()
	{
		return (String) getField("HTML_DOC", "<p>Требует реализации.</p>");
	}
	
	public abstract BigDecimal calculate();
}
