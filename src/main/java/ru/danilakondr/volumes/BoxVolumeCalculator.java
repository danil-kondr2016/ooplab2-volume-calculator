package ru.danilakondr.volumes;

import java.math.BigDecimal;

/**
 * Класс, вычисляющий объём прямоугольного параллелепипеда.
 * Объём вычисляется по формуле <code>$abc$</code>.
 * <p>
 * Принимает параметры:
 * <ul>
 * <li><code>a</code> &mdash; ширина прямоугольного параллелепипеда
 * <li><code>b</code> &mdash; длина прямоугольного параллелепипеда
 * <li><code>c</code> &mdash; высота прямоугольного параллелепипеда
 * </ul>
 * 
 * @author Данила А. Кондратенко
 * @since 0.1.1
 * @see Calculator
 */
public class BoxVolumeCalculator extends Calculator {
	private static final String[] PARAMETERS = new String[] {"a", "b", "c"};
	private static final String CALC_NAME = "Калькулятор объёма прямоугольного параллелепипеда";
	
	
	public BoxVolumeCalculator() {
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

	@Override
	public BigDecimal calculate() {
		BigDecimal a = getParameter("a");
		BigDecimal b = getParameter("b");
		BigDecimal c = getParameter("c");
		return a.multiply(b).multiply(c);
	}

}
