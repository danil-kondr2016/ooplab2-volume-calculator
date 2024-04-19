package ru.danilakondr.volumes;

import java.math.BigDecimal;

/**
 * Класс, вычисляющий объём пирамиды. Объём вычисляется по формуле
 * <code>$\frac13 S h$</code>.
 * <p>
 * Принимает параметры:
 * <ul>
 * <li><code>S</code> &mdash; площадь основания;
 * <li><code>h</code> &mdash; высота пирамиды.
 * </ul>
 * <p>
 * 
 * @author Данила А. Кондратенко
 * @since 0.2.3
 */
public class PyramidVolumeCalculator extends Calculator {
	@SuppressWarnings("unused")
	private static final String[] PARAMETERS = new String[] {"S", "h"};
	@SuppressWarnings("unused")
	private static final String CALC_NAME = "Калькулятор объёма пирамиды";
	@SuppressWarnings("unused")
	private static final String HTML_DOC = "<p>Объём пирамиды вычисляется по формуле "
			+ "\\[V = \\frac13 S h,\\] где \\(S\\) &mdash; это площадь основания, "
			+ "\\(h\\) &mdash; высота.</p>";
	
	
	public PyramidVolumeCalculator() {
		super();
	}

	@Override
	public BigDecimal calculate() {
		BigDecimal S = getParameter("S");
		BigDecimal h = getParameter("h");
		
		return S.multiply(h, ctx).divide(BigDecimal.valueOf(3L), ctx);
	}

}
