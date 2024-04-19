package ru.danilakondr.volumes;

import java.math.BigDecimal;

/**
 * Класс, вычисляющий объём конуса. 
 * Объём вычисляется по формуле <code>$\frac13 \pi R^2 h$</code>.
 * <p>
 * Принимает параметры:
 * <ul>
 * <li><code>R</code> &mdash; радиус основания;
 * <li><code>h</code> &mdash; высота конуса.
 * </ul>
 * <p>
 * 
 * @author Данила А. Кондратенко
 * @since 0.2.6
 */
public class ConeVolumeCalculator extends Calculator {
	@SuppressWarnings("unused")
	private static final String[] PARAMETERS = new String[] {"R", "h"};
	@SuppressWarnings("unused")
	private static final String CALC_NAME = "Калькулятор объёма конуса";
	@SuppressWarnings("unused")
	private static final String HTML_DOC = "<p>Объём конуса вычисляется по формуле "
			+ "\\[V = \\frac13 \\pi R^2 h,\\] где \\(R\\) &mdash; это радиус основания, "
			+ "\\(h\\) &mdash; высота.</p>";
	
	public ConeVolumeCalculator() {
		super();
	}

	@Override
	public BigDecimal calculate() {
		BigDecimal R = getParameter("R");
		BigDecimal h = getParameter("h");
		
		return R.pow(2, ctx).multiply(PI, ctx).multiply(h, ctx)
				.divide(BigDecimal.valueOf(3L), ctx);
	}

}
