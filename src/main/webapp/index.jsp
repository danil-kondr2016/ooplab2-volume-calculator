<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.math.*, ru.danilakondr.volumes.*, ru.danilakondr.volumes.web.CalculatorFactory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Калькулятор объёмов</title>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="katex.min.css">
<script defer src="katex.min.js"></script>
<script defer src="auto-render.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        renderMathInElement(document.body, {
          // customised options
          // • auto-render specific keys, e.g.:
          delimiters: [
              {left: '$$', right: '$$', display: true},
              {left: '$', right: '$', display: false},
              {left: '\\(', right: '\\)', display: false},
              {left: '\\[', right: '\\]', display: true}
          ],
          // • rendering keys, e.g.:
          throwOnError : false
        });
    });
</script>
</head>
<body>

<header>
<h1>Калькулятор объёмов</h1>
</header>

<main>
<h2>Выберите калькулятор: </h2>
<%
	for (String x : CalculatorFactory.getCalcTypes()) {
		out.print(String.format("<p><a href=\"form?type=%s\">%s</a></p>\r\n", x, CalculatorFactory.getCalcName(x)));
	}
 %>
</main>

</body>
</html>