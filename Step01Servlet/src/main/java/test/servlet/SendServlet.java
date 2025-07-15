package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/send")
public class SendServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 클라이언트가 요청을 하면서 전송한 요청 파라미터 추출하기
		 * 
		 * - HttpServletRequest 객체의 기능(method)을 이용해서 추출하면 된다. (tomcat 10 부터는 자동으로 됨)
		 * - post 방식 전송인 경우 추출하기 전에 인코딩 설정을 해줘야 한글이 깨지지 않는다
		 */
		// 입력한 이름 추출
		String uri = request.getRemoteHost();
		String name = request.getParameter("name");
		String msg = request.getParameter("msg");
		// 콘솔창에 출력해보기
		System.out.println(uri+" : " +name + " : " + msg);
		// 응답 인코딩 설정
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		// 응답 내용 출력
		PrintWriter pw = response.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>메세지 전송결과 페이지</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p>메세지 잘 받았어 클라이언트야</p>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
