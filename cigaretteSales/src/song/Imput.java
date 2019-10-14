package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//판매 상품 입고
@WebServlet("/imput")
public class Imput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String p_no = request.getParameter("p_no");
		String p_name = request.getParameter("p_name");
		
		request.setAttribute("p_no", p_no);
		request.setAttribute("p_name", p_name);
	
		request.setAttribute("title", "판매 상품 입고");
		request.setAttribute("view", "imput.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String p_no = request.getParameter("p_no");
		String i_cnt = request.getParameter("i_cnt");
			
		CigaretteVo vo = new CigaretteVo();
		
		System.out.println("p_no:"+p_no);
		System.out.println("i_cnt:"+i_cnt);
		
		vo.setP_no(p_no);
		vo.setI_cnt(i_cnt);
			
		int result = DAO.imput(vo);
		
		if(result == 0) {
			System.out.println("입고 실패");
			doGet(request,response);
		}
		if(result != 0) {
			DAO.sum(vo); //입고 성공시 입고수량올라가는것과같이 누적입고,잔존수량도 같이 오름
			System.out.println("입고 성공");
			response.sendRedirect("productManagement");
		}
	}

}
