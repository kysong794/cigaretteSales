package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//판매 상품 관리
@WebServlet("/productManagement")
public class productManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		CigaretteVo avo = (CigaretteVo) session.getAttribute("login");
		if(avo == null) {
			response.sendRedirect("login");
			return;
		}

		List<CigaretteVo> saleList = DAO.saleList();
		
		request.setAttribute("saleList", saleList);
		
		//삭제
		String p_no = request.getParameter("p_no");
		if(p_no != null) {
		System.out.println(p_no);
		CigaretteVo vo = new CigaretteVo();
		vo.setP_no(p_no);
		DAO.delete(vo);
		}
		
		request.setAttribute("title", "판매 상품 관리");
		request.setAttribute("view", "productManagement.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	
	}

}
