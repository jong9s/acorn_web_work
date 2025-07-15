<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = 1;
	String name = "종구입니다";
	String addr = "구리시";
	
	List<String> names = List.of("김구라", "서종훈", "해골");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jsp/test01.jsp</title>
</head>
<body>
	<h1>회원 한명의 정보 입니다</h1>
	<p>번호: <strong><%=num %></strong></p>
	<p>이름: <strong><%=name %>님</strong></p>
	<p>주소: <strong><%=addr %></strong></p>
	<ul>
		<li><%=name %></li>
	</ul>
	<ul>
	<%for(int i = 0; i < 3; i++) {%>
		<li><%=names.get(i)%></li>
	<%}%>
	</ul>
	<ul>
	<%for(String tmp : names) {%>
		<li><%=tmp%></li>
	<%}%>
	</ul>
</body>
</html>