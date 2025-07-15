<%@page import="test.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberDto> list = new MemberDao().selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/list.jsp</title>

<!-- Bootstrap CSS & JS -->
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	
	<!-- navBar -->
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="member" name="thisPage"/>
	</jsp:include>

	<!-- 본문 -->
	<div class="container mt-5">
	  <div class="d-flex justify-content-between align-items-center mb-3">
	    <h2>👥 회원 목록</h2>
	    <a class="btn btn-primary" href="${pageContext.request.contextPath }/member/insertform.jsp">
	      <i class="bi bi-person-plus-fill"></i> 회원 추가
	    </a>
	  </div>
	
	  <table class="table table-bordered table-hover align-middle text-center">
	    <thead class="table-success">
	      <tr>
	        <th>번호</th>
	        <th>이름</th>
	        <th>주소</th>
	        <th>수정</th>
	        <th>삭제</th>
	      </tr>
	    </thead>
	    <tbody>
	      <% for(MemberDto tmp : list) { %>
	        <tr>
	          <td><%= tmp.getNum() %></td>
	          <td><%= tmp.getName() %></td>
	          <td><%= tmp.getAddr() %></td>
	          <td>
	            <a class="btn btn-sm btn-warning" href="updateform.jsp?num=<%=tmp.getNum() %>">
	              <i class="bi bi-pencil-square"></i> 수정
	            </a>
	          </td>
	          <td>
	            <a class="btn btn-sm btn-danger delete-link" href="javascript:" data-num="<%= tmp.getNum()%>">
	              <i class="bi bi-trash3-fill"></i> 삭제
	            </a>
	          </td>
	        </tr>
	      <% } %>
	    </tbody>
	  </table>
	</div>
	<jsp:include page="/WEB-INF/include/footer.jsp"></jsp:include>
	<script>
		document.querySelectorAll(".delete-link").forEach(item=>{
			// item 은 클릭한 a 요소의 참조값
			item.addEventListener("click", (e)=>{
				// e.target 은 이벤트가 발생한 바로 그 요소의 참조값이다.
				// "click" 이벤트가 발생한 a 요소에 data-num 속성의 value 를 읽어오기
				const num = e.target.getAttribute("data-num");
				const isDelete = confirm(num + " 번 회원을 삭제 하시겠습니까?");
				if(isDelete) {
					// delete.jsp 페이지로 이동하게 하면서 삭제할 회원의 번호도 같이 전달되도록한다.
					location.href = "${pageContext.request.contextPath }/member/delete.jsp?num=" + num;

				}
			})
		});
	</script>
</body>
</html>
