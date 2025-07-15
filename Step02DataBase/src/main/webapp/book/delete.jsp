<%@page import="test.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	BookDao dao = new BookDao();
	dao.deleteByNum(num);
	// 새로운 경로로 요청을 다시 하라고 응답
	// String cPath = request.getContextPath();
	// HttpServletResponse 객체의 메소드를 이용해서 책 목록 페이지를 다시 요청하도록 한다 (페이지 refresh 효과)
	// response.sendRedirect(cPath + "/book/list.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/delete.jsp</title>
</head>
<body>
	<script>
		alert("삭제되었습니다.");
		location.href = "${pageContext.request.contextPath }/book/list.jsp";
	</script>
</body>
</html>