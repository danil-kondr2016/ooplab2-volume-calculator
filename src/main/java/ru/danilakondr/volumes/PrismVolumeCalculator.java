package ru.danilakondr.volumes;

import java.math.BigDecimal;

/**
 * Класс, вычисляющий объём прямоугольной призмы. 
 * Объём вычисляется по формуле <code>$S h$</code>.
 * <p>
 * Принимает параметры:
 * <ul>
 * <li><code>S</code> &mdash; площадь основания;
 * <li><code>h</code> &mdash; высота призмы.
 * </ul>
 * <p>
 * 
 * @author Данила А. Кондратенко
 * @since 0.2.4
 */
public class PrismVolumeCalculator extends Calculator {
	@SuppressWarnings("unused")
	private static final String[] PARAMETERS = new String[] {"S", "h"};
	@SuppressWarnings("unused")
	private static final String CALC_NAME = "Калькулятор объёма прямоугольной призмы";
	@SuppressWarnings("unused")
	private static final String HTML_DOC = "<p>Объём прямоугольной призмы вычисляется по формуле "
			+ "\\[V =  S h,\\] где \\(S\\) &mdash; это площадь основания, "
			+ "\\(h\\) &mdash; высота.</p>";
	
	public PrismVolumeCalculator() {
		super();
	}

	@Override
	public BigDecimal calculate() {
		BigDecimal S = getParameter("S");
		BigDecimal h = getParameter("h");
		
		return S.multiply(h, ctx);
	}

}
