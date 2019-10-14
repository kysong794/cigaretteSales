package song;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","cigaretteSales","hkit2019");
		System.out.println("DB연결");
		return con;
	}
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//판매 상품 관리
	public static List<CigaretteVo> saleList(){
		List<CigaretteVo> saleList = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select * from t_product "
					+" order by p_no desc ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CigaretteVo vo = new CigaretteVo();
				vo.setP_no(rs.getString("p_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_cnt_sum(rs.getString("p_cnt_sum"));
				vo.setP_cnt_rem(rs.getString("p_cnt_rem"));
				vo.setP_price(rs.getString("p_price"));
				saleList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return saleList;
	}
	
	//판매 상품 정정
	public static int mod(CigaretteVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_product "
					+" set p_name = ?, p_price = ? "
					+" where p_no = ? ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,vo.getP_name());
			ps.setString(2, vo.getP_price());
			ps.setString(3, vo.getP_no());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	//판매 상품 삭제
	public static void delete(CigaretteVo vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE t_product "
					+" WHERE p_no = ?";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getP_no());
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
	}
	//판매 상품 입고
	public static int imput(CigaretteVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_import (i_no,p_no,i_cnt) "
					+" VALUES ((select nvl(max(i_no),0)+1 from t_import),?,?) ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, vo.getP_no());
			ps.setString(2, vo.getI_cnt());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	//입고시 입고수량과 누적입고,잔존수량 셋이 동시에 오름
	public static void sum(CigaretteVo vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_product "
					+" set p_cnt_rem = p_cnt_rem + ? ,p_cnt_sum = p_cnt_sum + ? "
					+" where p_no = ? ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getI_cnt());
			ps.setString(2, vo.getI_cnt());
			ps.setString(3, vo.getP_no());
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
	}
	
	//신규 상품 추가
	public static int newProduct(CigaretteVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_product (p_no,p_name,P_price) " 
					+" VALUES ((select nvl(max(p_no),0)+1 from t_product),?,?) ";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getP_name());
			ps.setString(2, vo.getP_price());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	//매출 관리
	public static List<CigaretteVo> salesManagement(){
		List<CigaretteVo> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select to_char(a.i_date,'yyyy-mm-dd')as i_date,"
					+" a.i_no,1 as typ,a.p_no,b.p_name,b.p_cnt_sum,"
					+" (a.i_cnt*b.p_price)*-0.8 as totalsum "
					+" from t_import a "
					+" inner join t_product b on a.p_no = b.p_no "
					+" union all "
					+" select to_char(a.s_date,('yyyy-mm-dd'))as s_date, "
					+" a.s_no,2 as typ,a.p_no,b.p_name,a.s_q,"
					+" (a.s_q*b.p_price) as totalsumn "
					+" from t_sell a "
					+" inner join t_product b on b.p_no = a.p_no ";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				CigaretteVo vo = new CigaretteVo();
				vo.setI_date(rs.getString("i_date"));
				vo.setI_no(rs.getString("i_no"));
				vo.setTyp(rs.getString("typ"));
				vo.setP_no(rs.getString("p_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_cnt_sum(rs.getString("p_cnt_sum"));
				vo.setTotalsum(rs.getString("totalsum"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return list;
	}
	
	//신규 매출
	public static int newSale(CigaretteVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_sell (s_no,p_no,s_q) " 
					+" VALUES ((select nvl(max(s_no),0)+1 from t_sell),?,?) ";
					
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, vo.getS_no());
			ps.setString(2, vo.getS_q());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}		
		return result;
	}
	
	//신규매출 성공시 잔존수량,누적수량 변경
	public static void p_cnt(CigaretteVo vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update t_product "
					+" set p_cnt_sum = p_cnt_sum - ? , p_cnt_rem = p_cnt_rem - ? "
					+" where p_no = ? ";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getS_q());
			ps.setString(2, vo.getS_q());
			ps.setString(3, vo.getP_no());
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}		
	}
	//s_no 가져오기
	public static List<CigaretteVo> t_sellList(){
		List<CigaretteVo> t_sellList = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from t_sell";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CigaretteVo vo = new CigaretteVo();
				vo.setS_no(rs.getString("s_no"));
				vo.setP_no(rs.getString("p_no"));
				vo.setS_q(rs.getString("s_q"));
				vo.setS_date(rs.getString("s_date"));
				t_sellList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}	
		return t_sellList;
	}
	
	//관리자 로그인
	public static int login(CigaretteVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select a_pw from t_account "
					+" where a_id = ? ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,vo.getA_id());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String a_pw = rs.getString("a_pw");
				if(a_pw.equals(vo.getA_pw())) {
					vo.setA_pw(""); //비번을 지움
					result = 1; //로그인 성공
				}else {
					result = -1; //비번이 틀림
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return result;
	}
}
