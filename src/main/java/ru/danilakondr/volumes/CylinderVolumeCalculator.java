package ru.danilakondr.volumes;

import java.math.BigDecimal;

/**
 * Класс, вычисляющий объём цилиндра. 
 * Объём вычисляется по формуле <code>$\pi R^2 h$</code>.
 * <p>
 * Принимает параметры:
 * <ul>
 * <li><code>R</code> &mdash; радиус основания;
 * <li><code>h</code> &mdash; высота цилиндра.
 * </ul>
 * <p>
 * 
 * @author Данила А. Кондратенко
 * @since 0.2.5
 */
public class CylinderVolumeCalculator extends Calculator {
	@SuppressWarnings("unused")
	private static final String[] PARAMETERS = new String[] {"R", "h"};
	@SuppressWarnings("unused")
	private static final String CALC_NAME = "Калькулятор объёма цилиндра";
	@SuppressWarnings("unused")
	private static final String HTML_DOC = "<p>Объём цилиндра вычисляется по формуле "
			+ "\\[V = \\pi R^2 h,\\] где \\(R\\) &mdash; это радиус основания, "
			+ "\\(h\\) &mdash; высота.</p>";
	
	public CylinderVolumeCalculator() {
		super();
	}

	@Override
	public BigDecimal calculate() {
		BigDecimal R = getParameter("R");
		BigDecimal h = getParameter("h");
		
		return R.pow(2, ctx).multiply(PI, ctx).multiply(h, ctx);
	}

}
