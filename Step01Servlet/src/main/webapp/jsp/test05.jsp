<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// DB 에 저장되어 있었던 nick name 이라고 가정하자
	String nickName = "짱구";
	String gender = "man"; // "man" or "woman" 이 DB 에 저장되어 있다고 생각하면 된다.
	String job = "developer"; // "student" or "developer" or "etc"
	// textarea 에 입력한 내용이 DB 에 저장되어 있었다고 가정
	String comment = "날씨가 너무 덥네요 \n 하지만 날은 좋네요 \n \t 들여쓰기";
	// DB 에 저장된 취미 정보라고 가정
	String hobbys = "[\"game\",\"music\"]"; // ["game", "music"]
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jsp/test05.jsp</title>
</head>
<body>
	<h1>어떤 정보를 수정하는 폼</h1>
	<p>
		최초 출력할 때는 DB 에 저장된 정보를 이용해서 여러가지 form 요소들을 출력해준다.
	</p>
	<form action="update.jsp" method = "get">
		<div>
			<lavel for="nick">닉네임</lavel>
			<input type="text" name="nick" id="nick" value="<%=nickName %>"/>
		</div>
		<fieldset>
			<legend>성별</legend>
			<label>
				<input type="radio" name="gender" value="man" <%=gender.equals("man") ? "checked" : "" %>/> 남자
			</label>
			<label>
				<input type="radio" name="gender" value="woman" <%=gender.equals("woman") ? "checked" : "" %>/> 여자
			</label>
		</fieldset>
		<div>
			<lavel for="job">직업</lavel>
			<select name="job" id="job">
				<option value="student" <%=job.equals("student") ? "selected" : "" %>>학생</option>
				<option value="developer" <%=job.equals("developer") ? "selected" : "" %>>개발자</option>
				<option value="etc" <%=job.equals("etc") ? "selected" : "" %>>기타</option>
			</select>
		</div>
		<div>
			<lavel for="comment">하고싶은 말</lavel><br />
			<textarea name="comment" id="comment" rows="5"><%=comment %></textarea>
		</div>
		<fieldset>
			<legend>취미(여러개 선택가능)</legend>
			<label>
				<input type="checkbox" name="hobby" value="game" <%=hobbys.contains("game") ? "checked" : "" %>/>게임
			</label>
			<label>
				<input type="checkbox" name="hobby" value="sports" <%=hobbys.contains("sports") ? "checked" : "" %>/>운동
			</label>
			<label>
				<input type="checkbox" name="hobby" value="music" <%=hobbys.contains("music") ? "checked" : "" %>/>음악
			</label>
		</fieldset>
		<button type="submit">수정</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>