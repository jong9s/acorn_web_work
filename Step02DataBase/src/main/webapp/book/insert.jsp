<%@page import="test.dao.BookDao"%>
<%@page import="test.dto.BookDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String title = request.getParameter("title");
	String author = request.getParameter("author");
	String pub = request.getParameter("pub");

	BookDto dto = new BookDto();
	dto.setTitle(title);
	dto.setAuthor(author);
	dto.setPub(pub);

	boolean isSuccess = new BookDao().insert(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/insert.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
	<div class="container mt-5">
		<div class="card shadow p-4">
			<% if (isSuccess) { %>
				<div class="alert alert-success mt-5" role="alert">
					<i class="bi bi-check"></i>
					<strong><%= title %></strong>책을 성공적으로 추가했습니다.
					<a href="${pageContext.request.contextPath }/book/list.jsp" class="btn btn-primary">책 목록 보기</a>
				</div>
			<% } else { %>
				<div class="alert alert-danger mt-5" role="alert">
					<i class="bi bi-x"></i>
					책 추가에 실패했습니다.
					<a href="${pageContext.request.contextPath }/book/insertform.jsp" class="btn btn-secondary">다시 입력하기</a>
				</div>
			<% } %>
		</div>
	</div>
</body>
</html>
