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
 * @see Calculator
 */
public class CubeVolumeCalculator extends Calculator {

	public CubeVolumeCalculator() {
		super();
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
