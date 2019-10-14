package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//매출 관리
@WebServlet("/SalesManagement")
public class SalesManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		CigaretteVo avo = (CigaretteVo)session.getAttribute("login");
		if(avo == null) {
			response.sendRedirect("login");
			return;
		}
		
		List<CigaretteVo> list = DAO.salesManagement();
		request.setAttribute("list", list);
		
		request.setAttribute("title", "매출관리");
		request.setAttribute("view", "SalesManagement.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
	
	}

}
