package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//신규 상품 추가
@WebServlet("/newproduct")
public class NewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		request.setAttribute("title", "신규 상품 추가");
		request.setAttribute("view", "newProduct.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String p_name = request.getParameter("p_name");
		String p_price = request.getParameter("p_price");
		
		System.out.println("p_name:"+p_name);
		System.out.println("p_price:"+p_price);
		
		CigaretteVo vo = new CigaretteVo();
		
		vo.setP_name(p_name);
		vo.setP_price(p_price);
		
		int result = DAO.newProduct(vo);
		
		if(result == 0) {
			System.out.println("신규 상품 등록 실패");
			doGet(request,response);
		}
		if(result != 0) {
			System.out.println("신규 상품 등록 성공");
			response.sendRedirect("productManagement");
		}
		
		
	}

}
