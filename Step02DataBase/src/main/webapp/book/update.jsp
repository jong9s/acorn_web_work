<%@page import="test.dao.BookDao"%>
<%@page import="test.dto.BookDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String title = request.getParameter("title");
	String author = request.getParameter("author");
	String pub = request.getParameter("pub");
	
	BookDto dto = new BookDto();
	dto.setNum(num);
	dto.setTitle(title);
	dto.setAuthor(author);
	dto.setPub(pub);
	
	BookDao dao = new BookDao();
	boolean isSuccess = dao.update(dto);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/update.jsp</title>
</head>
<body>
		<%if(isSuccess) {%>
			<script>
				alert("<%=title %>수정되었습니다.");
				location.href="${pageContext.request.contextPath }/book/list.jsp";
			</script>
		<%} else{ %>
			<p>수정 실패</p>
			<a href="${pageContext.request.contextPath }/book/updateform.jsp?num=<%=num %>">다시 수정하기</a>
		<%} %>
</body>
</html>