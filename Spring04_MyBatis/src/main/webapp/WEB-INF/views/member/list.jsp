<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/list.jsp</title>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath }/member/insert-form">회원 추가</a>
		<h1>회원 목록</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>주소</th>
					<th>수정</th>
					<th>삭제</th>				
				</tr>
			</thead>
			<tbody>
				<%--
					"list" 라는 키 값으로 담긴 데이터는 List<MemberDto> 이다
					따라서 tmp 는 MemberDto type 이다
					tmp.getNum() 하면 번호를 얻어낼 수 있는데 EL 에서는 tmp.num 해도 자동으로 getter 메소드를
					호출해준다.
				--%>
				<c:forEach var="tmp" items="${list }">
					<tr>
						<td>${tmp.num }</td>
						<td>${tmp.name }</td>
						<td>${tmp.addr }</td>
						<td><a href="edit?num=${tmp.num }">수정</a></td>
						<td><a href="delete?num=${tmp.num }">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="${pageContext.request.contextPath }/">인덱스로</a>
	</div>
</body>
</html>