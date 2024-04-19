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
	@SuppressWarnings("unused")
	private static final String[] PARAMETERS = new String[] {"a"};
	@SuppressWarnings("unused")
	private static final String CALC_NAME = "Калькулятор объёма куба";
	@SuppressWarnings("unused")
	private static final String HTML_DOC = "<p>Объём куба вычисляется по формуле \\[V = a^3,\\] где \\(a\\) &mdash; это сторона куба</p>";
	
	public CubeVolumeCalculator() {
		super();
	}
	
	/**
	 * Вычисляет объём куба.
	 */
	@Override
	public BigDecimal calculate() {
		BigDecimal a = getParameter("a");
		
		return a.pow(3, ctx);
	}
}
