package ru.danilakondr.volumes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Класс, вычисляющий объём шара. Объём шара вычисляется по формуле
 * <code>$\frac43 \pi R^3$</code>.
 * <p>
 * Принимает параметры:
 * <ul>
 * <li><code>R</code> &mdash; радиус шара
 * </ul>
 * <p>
 * При отображении используется точность в 20 значащих цифр, при
 * вычислении &mdash; в 40.
 * 
 * @author Данила А. Кондратенко
 * @since 0.2.1
 */
public class BallVolumeCalculator extends Calculator {
	@SuppressWarnings("unused")
	private static final String[] PARAMETERS = new String[] {"R"};
	@SuppressWarnings("unused")
	private static final String CALC_NAME = "Калькулятор объёма шара";
	@SuppressWarnings("unused")
	private static final String HTML_DOC = "<p>Объём шара вычисляется по формуле \\[V = \\frac43\\pi R^3,\\] где \\(R\\) &mdash; это радиус шара.</p>";
	
	private static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971");
	private static final int CALC_SCALE = 40;
	private static final int REPR_SCALE = 20;
	
	private static final MathContext CALC_CONTEXT = new MathContext(CALC_SCALE, RoundingMode.HALF_EVEN);
	private static final MathContext REPR_CONTEXT = new MathContext(REPR_SCALE, RoundingMode.HALF_EVEN);
	
	public BallVolumeCalculator() {
		super();
	}

	@Override
	public BigDecimal calculate() {
		BigDecimal radius = getParameter("R");
		
		return radius.pow(3)
				.multiply(BigDecimal.valueOf(4L), CALC_CONTEXT)
				.divide(BigDecimal.valueOf(3L), CALC_CONTEXT)
				.multiply(PI, CALC_CONTEXT)
				.round(REPR_CONTEXT);
	}

}
