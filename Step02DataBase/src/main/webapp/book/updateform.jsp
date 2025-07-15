<%@page import="test.dto.BookDto"%>
<%@page import="test.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	BookDto dto = new BookDao().getByNum(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/updateform.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
	<div class="container mt-5">
		<div class="card shadow p-4">
			<h1 class="text-center mb-4">📖 책 정보 수정</h1>
			<form action="${pageContext.request.contextPath }/book/update.jsp" method="post">
				<div class="mb-3">
					<label for="num" class="form-label">번호</label>
					<input type="text" name="num" id="num" class="form-control" value="<%=dto.getNum() %>" readonly>
				</div>
				<div class="mb-3">
					<label for="title" class="form-label">제목</label>
					<input type="text" name="title" id="title" class="form-control" value="<%=dto.getTitle() %>" required>
				</div>
				<div class="mb-3">
					<label for="author" class="form-label">저자</label>
					<input type="text" name="author" id="author" class="form-control" value="<%=dto.getAuthor() %>" required>
				</div>
				<div class="mb-3">
					<label for="pub" class="form-label">출판사</label>
					<input type="text" name="pub" id="pub" class="form-control" value="<%=dto.getPub() %>" required>
				</div>
				<div class="mt-4">
					<button type="submit" class="btn btn-warning">
						<i class="bi bi-pencil-square"></i> 수정하기
					</button>
					<button type="reset" class="btn btn-secondary">초기화</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
