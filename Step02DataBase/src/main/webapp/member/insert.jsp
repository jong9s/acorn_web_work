<%@page import="test.dao.MemberDao"%>
<%@page import="test.dto.MemberDto"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="test.util.DbcpBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 폼 전송되는 추가할 회원의 이름과 주소를 추출한다.
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	// DB 에 저장하기 위해 name, addr 을 MemberDto 객체에 담는다.
	MemberDto dto = new MemberDto();
	dto.setName(name);
	dto.setAddr(addr);
	// 2. DB 에 저장한다.
	MemberDao dao = new MemberDao();
	boolean isSuccess = dao.insert(dto);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insert.jsp</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<%if (isSuccess) {%>
	<p>
		<strong><%=name %></strong> 님의 정보를 성공적으로 저장했습니다.
		<a href="${pageContext.request.contextPath }/member/list.jsp">회원목록 보러가기</a>
	</p>
	<%} else { %>
	<p>
		회원 정보 저장 실패
		<a href="${pageContext.request.contextPath }/member/insertform.jsp">다시 입력하기</a>
	</p>
	<%} %>
	
	
</body>
</html>