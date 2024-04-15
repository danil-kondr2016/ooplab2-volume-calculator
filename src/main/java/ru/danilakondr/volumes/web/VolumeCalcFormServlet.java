package ru.danilakondr.volumes.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
 * Servlet implementation class VolumeCalcFormServlet
 */
public class VolumeCalcFormServlet extends VolumeCalculatorServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolumeCalcFormServlet() {
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
		
		String locType = calc.getName();
		
		StringBuilder formList = new StringBuilder();
		Arrays.stream(calc.getParameters())
			.map((p) -> String.format("<p>\\\\(%s = \\\\)<input name=\"%s\"></p>\r\n", p, p))
			.forEach((x) -> formList.append(x));
		
		Map<String, String> vars = Map.of("calc-name", locType, "calc-type", query.get("type"), "parameters", formList.toString(), "documentation", calc.getDocumentation());
		
		try (InputStream is = request.getServletContext().getResourceAsStream("/WEB-INF/form.html")) {
			Pattern varPattern = Pattern.compile("\\$\\{(.*?)\\}");
			String template = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			StringBuilder output = new StringBuilder();
			
			Matcher m = varPattern.matcher(template);
			while (m.find()) {
				m.appendReplacement(output, vars.get(m.group(1)));
			}
			m.appendTail(output);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().append(output);
		}
	}
}
