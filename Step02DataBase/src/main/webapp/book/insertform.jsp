<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/insertform.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
	<div class="container mt-5">
		<h1 class="mb-4">📚 책 추가 양식</h1>
		<form action="${pageContext.request.contextPath }/book/insert.jsp" method="post">
			
			<div class="mb-3">
				<label class="form-label" for="title">제목</label>
				<input class="form-control" type="text" name="title" id="title" required>
			</div>
			
			<div class="mb-3">
				<label class="form-label" for="author">저자</label>
				<input class="form-control" type="text" name="author" id="author" required>
			</div>
			
			<div class="mb-3">
				<label class="form-label" for="pub">출판사</label>
				<input class="form-control" type="text" name="pub" id="pub" required>
			</div>
			
			<button class="btn btn-primary mt-3" type="submit">
				<i class="bi bi-floppy2-fill"></i>
				책 추가하기	
			</button>
		</form>
	</div>
</body>
</html>
