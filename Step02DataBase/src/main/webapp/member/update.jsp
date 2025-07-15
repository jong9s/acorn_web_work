<%@page import="test.dao.MemberDao"%>
<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	// 1. form 전송되는 
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");

	MemberDao dao = new MemberDao();
	MemberDto dto = new MemberDto();
	dto.setNum(num);
	dto.setName(name);
	dto.setAddr(addr);
	
	boolean isSuccess = dao.update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
</head>
<body>

	<% if(isSuccess) {%>
	<script>
		alert("수정되었습니다");
		location.href="${pageContext.request.contextPath }/member/list.jsp";
	</script>
	<%} else { %>
		<p>
			수정실패! <a href="updateform.jsp?num=<%=num %>">다시 수정하러 가기</a>
		</p>
	<%} %>
	
</body>
</html>