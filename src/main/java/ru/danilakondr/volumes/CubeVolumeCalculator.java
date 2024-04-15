package ru.danilakondr.volumes;

import java.math.BigDecimal;

/**
 * Класс, вычисляющий объём куба. Объём куба вычисляется по
 * формуле <code>$a^3$</code>.
 * <p>
 * Принимает параметры:
 * <ul>
 * <li><code>a</code> &mdash; сторона куба
 * </ul>
 * 
 * @author Данила А. Кондратенко
 * @since 0.1.0
 * @see Calculator
 */
public class CubeVolumeCalculator extends Calculator {
	private static final String[] PARAMETERS = new String[] {"a"};
	private static final String CALC_NAME = "Калькулятор объёма куба";
	
	public CubeVolumeCalculator() {
		super();
	}
	
	@Override
	public String[] getParameters() {
		return PARAMETERS;
	}
	
	@Override
	public String getName() {
		return CALC_NAME;
	}

	/**
	 * Вычисляет объём куба.
	 */
	@Override
	public BigDecimal calculate() {
		BigDecimal a = getParameter("a");
		
		return a.pow(3);
	}
}
