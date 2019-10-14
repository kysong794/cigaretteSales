package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//신규 매출
@WebServlet("/newSale")
public class newSale extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		request.setAttribute("title", "신규 매출");
		request.setAttribute("view", "newSale.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String s_no = request.getParameter("s_no");
		String s_q = request.getParameter("s_q");
		
		CigaretteVo vo = new CigaretteVo();
		
		vo.setS_no(s_no);
		vo.setS_q(s_q);
		
		int result = DAO.newSale(vo);
		
		if(result ==0) {
			System.out.println("신규매출 추가 실패");
			doGet(request,response);
		}
		if(result !=0) {
			DAO.p_cnt(vo);
			System.out.println("신규매출 추가 성공");
			response.sendRedirect("SalesManagement");
		}
		
	
	}

}
