package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//로그인
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		
		
		request.setAttribute("title", "관리자 로그인");
		request.setAttribute("view", "login.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String a_id = request.getParameter("a_id");
		String a_pw = request.getParameter("a_pw");
		
		CigaretteVo vo = new CigaretteVo();
		
		vo.setA_id(a_id);
		vo.setA_pw(a_pw);
		
		int result = DAO.login(vo);
		
		if(result == 0) {
			System.out.println("로그인 실패");
			doGet(request, response);
		}
		if(result == -1) {
			System.out.println("비밀번호 틀림");
			doGet(request, response);
		}
		if(result == 1) {
			System.out.println("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("login", vo);
			response.sendRedirect("home.jsp");
		}
		
	}

}
