package ru.danilakondr.volumes;

import java.util.HashMap;
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
	private HashMap<String, BigDecimal> parameters;
	
	public Calculator() {
		parameters = new HashMap<>();
	}

	/**
	 * Устанавливает значение параметра.
	 * 
	 * @param name название параметра
	 * @param value значение параметра
	 */
	public void setParameter(String name, BigDecimal value) {
		parameters.put(name, value);
	}
	
	/**
	 * Получает значение параметра. По умолчанию возвращает
	 * <code>BigDecimal.ZERO</code>.
	 * @param name название параметра
	 * @return значение параметра
	 */
	public BigDecimal getParameter(String name) {
		return parameters.getOrDefault(name, BigDecimal.ZERO);
	}

	public abstract String[] getParameters();
	
	public abstract String getName();
	
	public abstract BigDecimal calculate();
}
