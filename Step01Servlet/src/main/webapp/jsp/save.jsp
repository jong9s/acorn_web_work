<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. form 전송되는 숫자를 읽어와서 실제 정수로 만들어준다.
	
	// 2. 해당 숫자가 짝수면 "전송한 숫자 x 는 짝수입니다."
	//	  해당 숫자가 홀수면 "전송한 숫자 x 는 홀수입니다."
	//    를 콘솔창이 아닌 클라이언트 웹 브라우저에 출력하는 프로그래밍을 해보세요
	
	int num = Integer.parseInt(request.getParameter("inputNum"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jsp/save.jsp</title>
</head>
<body>
	<% if(num % 2 == 0){ %>
		<h1><%=num %> 은 짝수입니다</h1>
	<%} else{%>
		<h1><%=num %> 은 홀수입니다</h1>
	<%} %>
	
	<h3>3항 연산자 이용</h3>
	<p>입력한 숫자 <%=num %> 은 <%= num%2 == 0 ? "짝수":"홀수" %> 입니다</p>
</body>
</html>