package ru.danilakondr.volumes.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.danilakondr.volumes.Calculator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Сервлет, отвечающий за вычисления и отображение значений объёмов.
 * Использует внутренние ресурсы и класс CalculatorFactory, который
 * по названию создаёт объект калькулятора и загружает в него все
 * возможные параметры.
 * 
 * @author Данила А. Кондратенко
 * @since 0.1.0
 */
public class VolumeCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolumeCalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		preventCaching(request, response);
		
		HashMap<String, String> query = QueryUtils.getQueryMap(request.getQueryString());
		Calculator calc = CalculatorFactory.createCalc(query);
		BigDecimal answer = calc.calculate();
		
		String locType = calc.getName();
		
		StringBuilder parametersString = new StringBuilder();
		parametersString.append("<ul>\r\n");
		Arrays.stream(calc.getParameters())
			.map((p) -> String.format("  <li>\\(%s = %s\\)</li>\r\n", p, calc.getParameter(p)))
			.forEach((x) -> parametersString.append(x));
		parametersString.append("</ul>\r\n");
		
		Map<String, String> vars = Map.of("calc-type", locType, "parameters", parametersString.toString(), "answer", answer.toString(), "precision", String.valueOf(calc.getPrecision()));
		
		try (InputStream is = request.getServletContext().getResourceAsStream("/WEB-INF/answer.html")) {
			Pattern varPattern = Pattern.compile("\\$\\{(.*?)\\}");
			String template = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			StringBuilder output = new StringBuilder();
			
			Matcher m = varPattern.matcher(template);
			while (m.find()) {
				m.appendReplacement(output, vars.get(m.group(1)).replace("\\", "\\\\"));
			}
			m.appendTail(output);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().append(output);
		}
	}
	
	/** 
	 * Prevents navigator from caching data.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 */
	protected void preventCaching(HttpServletRequest request, HttpServletResponse response) {
		/* see http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
		String protocol = request.getProtocol();
		if ("HTTP/1.0".equalsIgnoreCase(protocol)) {
				response.setHeader("Pragma", "no-cache");
		} else if ("HTTP/1.1".equalsIgnoreCase(protocol)) {
			response.setHeader("Cache-Control", "no-cache");
		}
		response.setDateHeader("Expires", 0);
	}
}
