package song;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//판매 상품 정정
@WebServlet("/mod")
public class Mod extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String p_no = request.getParameter("p_no");
		String p_name = request.getParameter("p_name");
		String p_price = request.getParameter("p_price");
		
		request.setAttribute("p_no", p_no);
		request.setAttribute("p_name", p_name);
		request.setAttribute("p_price", p_price);
		
		request.setAttribute("title", "판매 상품 정정");
		request.setAttribute("view", "mod.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String p_no = request.getParameter("p_no");
		String p_name = request.getParameter("p_name");
		String p_price = request.getParameter("p_price");
		
		System.out.println("p_no:"+p_no);
		System.out.println("p_name:"+p_name);
		System.out.println("p_price:"+p_price);
		
		CigaretteVo vo = new CigaretteVo();
		
		vo.setP_no(p_no);
		vo.setP_name(p_name);
		vo.setP_price(p_price);
		
		int result = DAO.mod(vo);
		
		if(result == 0) {
			System.out.println("정정 실패");
			doGet(request,response);
		}
		if(result != 0) {
			System.out.println("정정 성공");
			response.sendRedirect("productManagement");
		}
	}

}
