<%@page import="java.net.URLEncoder"%>
<%@page import="org.mindrot.jbcrypt.BCrypt"%>
<%@page import="test.dao.UserDao"%>
<%@page import="test.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userName = request.getParameter("userName");
	String password = request.getParameter("password");
	String url = request.getParameter("url");
	String encodedUrl = URLEncoder.encode(url, "UTF-8");

	boolean isValid = false;
	UserDto dto = UserDao.getInstance().getByUserName(userName);
	if(dto != null){
		isValid = BCrypt.checkpw(password, dto.getPassword());
	}
	if(isValid){
		session.setAttribute("userName", userName);
		// role 정보도 저장한다
		session.setAttribute("role", dto.getRole());
		session.setMaxInactiveInterval(60*60);
	}
	
	// 체크박스를 체크한 상태로 로그인 버튼을 누르면 null 이 아니다. (체크하지 않으면 null)
	String isSave = request. getParameter("isSave");
	if(isSave != null) {
		// 입력한 아이디 비밀번호를 쿠키로 응답하고 1주일 동안 유지 되도록한다.
		// 아이디 비밀번호를 쿠키로 응답하고 1주일 동안 유지되도록 한다.
		Cookie cook1 = new Cookie("savedUserName", userName);
		Cookie cook2 = new Cookie("savedPassword", password);
		// 쿠키 유지시간 초 단위로 설정
		cook1.setMaxAge(60*60*24*7);
		cook2.setMaxAge(60*60*24*7);
		// 쿠키 경로
		// cook1.setPath("/"); // 모든 경로에서 사용할 수 있는 쿠키
		// cook1.setPath("/user/"); // user 경로에서만 사용할 수 있는 쿠키
		// 설정하지 않으면 현재 경로에서만 사용할 수 있음
		
		// HttpServeletResponse 객체에 Cookie 객체를 담으면 응답할때 알아서 쿠키가 응답된다.
		response.addCookie(cook1);
		response.addCookie(cook2);
	} else { // 체크박스를 체크하지 않았으면 쿠키를 삭제한다.
		// 특정 키 값으로 저장된 쿠키값 삭제하기(value 에는 아무 값이나 넣어도 상관없다)
		Cookie cook1 = new Cookie("savedUserName", "");
		Cookie cook2 = new Cookie("savedPassword", null);
		// 쿠키 유지시간을 0으로 설정해서 응답하면 쿠키가 삭제되는 효과를 낸다
		cook1.setMaxAge(0);
		cook2.setMaxAge(0);
		response.addCookie(cook1);
		response.addCookie(cook2);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp" />
<% if(isValid){ %>
<!-- 로그인 성공 시 2초 뒤 자동 이동 -->
<meta http-equiv="refresh" content="2;url=<%=url%>" />
<% } %>
</head>
<body>
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="login" name="thisPage"/>
	</jsp:include>

	<div class="container mt-5">
		<% if(isValid){ %>
			<div class="alert alert-success text-center">
				<strong><%=userName%></strong>님, 환영합니다! 로그인에 성공했습니다.<br/>
				잠시 후 이동하거나 <a href="<%=url%>" class="alert-link">여기</a>를 클릭하세요.
			</div>
		<% } else { %>
			<div class="alert alert-danger text-center">
				아이디 혹은 비밀번호가 잘못되었습니다.<br/>
				<a href="loginform.jsp?url=<%=encodedUrl%>" class="btn btn-danger mt-3">다시 로그인</a>
			</div>
		<% } %>
	</div>

	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>