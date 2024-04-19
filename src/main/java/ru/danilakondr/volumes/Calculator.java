package ru.danilakondr.volumes;

import java.util.HashMap;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
	private static final String[] _EMPTY_PARAM_LIST = new String[0];
	private HashMap<String, BigDecimal> parameters;
	protected MathContext ctx;
	
	private static final int DEFAULT_PRECISION = 20;
	private static final int MIN_PRECISION = 3;
	private static final int MAX_PRECISION = 1000;
	
	public static final BigDecimal PI = new BigDecimal(
			"3.1415926535897932384626433832795028841971693993"
			+ "75105820974944592307816406286208998628034825342"
			+ "11706798214808651328230664709384460955058223172"
			+ "53594081284811174502841027019385211055596446229"
			+ "48954930381964428810975665933446128475648233786"
			+ "78316527120190914564856692346034861045432664821"
			+ "33936072602491412737245870066063155881748815209"
			+ "20962829254091715364367892590360011330530548820"
			+ "46652138414695194151160943305727036575959195309"
			+ "21861173819326117931051185480744623799627495673"
			+ "51885752724891227938183011949129833673362440656"
			+ "64308602139494639522473719070217986094370277053"
			+ "92171762931767523846748184676694051320005681271"
			+ "45263560827785771342757789609173637178721468440"
			+ "90122495343014654958537105079227968925892354201"
			+ "99561121290219608640344181598136297747713099605"
			+ "18707211349999998372978049951059731732816096318"
			+ "59502445945534690830264252230825334468503526193"
			+ "11881710100031378387528865875332083814206171776"
			+ "69147303598253490428755468731159562863882353787"
			+ "59375195778185778053217122680661300192787661119"
			+ "59092164201989"
		);
	
	public Calculator() {
		parameters = new HashMap<>();
		ctx = new MathContext(DEFAULT_PRECISION, RoundingMode.HALF_EVEN);
	}

	/**
	 * Установить значение параметра.
	 * 
	 * @param name название параметра
	 * @param value значение параметра
	 */
	public void setParameter(String name, BigDecimal value) {
		parameters.put(name, value);
	}
	
	/**
	 * Получить значение параметра.
	 * 
	 * @param name название параметра
	 * @return значение параметра, по умолчанию &mdash; <code>BigDecimal.ZERO</code>
	 */
	public BigDecimal getParameter(String name) {
		return parameters.getOrDefault(name, BigDecimal.ZERO);
	}

	private Object getField(String name, Object defObj)
	{
		Class<? extends Calculator> c = this.getClass();
		
		try {
			Field field = c.getDeclaredField(name);
			field.setAccessible(true);
			Object obj = field.get(this);
			
			return obj;
		}
		catch (NoSuchFieldException e) {
			return defObj;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @return список параметров
	 */
	public String[] getParameters()
	{
		return (String[]) getField("PARAMETERS", _EMPTY_PARAM_LIST);
	}
	
	/**
	 * @return название калькулятора
	 */
	public String getName()
	{
		return (String) getField("CALC_NAME", "Неизвестный калькулятор");
	}
	
	/**
	 * @return документация в формате HTML
	 */
	public String getDocumentation()
	{
		return (String) getField("HTML_DOC", "<p>Требует реализации.</p>");
	}
	
	/**
	 * 
	 * @param prec точность
	 * @return true, если точность установлена правильно,
	 *         false, если точность не установлена или установлена на максимум
	 */
	public boolean setPrecision(int prec)
	{
		if (prec >= MAX_PRECISION) {
			ctx = new MathContext(MAX_PRECISION, RoundingMode.HALF_EVEN);
			return false;
		}
		
		if (prec <= MIN_PRECISION) {
			ctx = new MathContext(MIN_PRECISION, RoundingMode.HALF_EVEN);
			return false;
		}
		
		ctx = new MathContext(prec, RoundingMode.HALF_EVEN);
		return true;
	}
	
	public int getPrecision() {
		return ctx.getPrecision();
	}
	
	public abstract BigDecimal calculate();
}
