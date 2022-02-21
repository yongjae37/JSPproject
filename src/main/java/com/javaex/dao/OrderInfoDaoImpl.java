package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.OrderInfoVo;

public class OrderInfoDaoImpl implements OrderInfoDao {

	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@3.38.190.21:1521:xe";
			conn = DriverManager.getConnection(dburl, "mysiteB", "1234");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버 로드 실패!");
		}
		return conn;
	}

	private static OrderInfoDao instance;
	public static OrderInfoDao getInstance() {
		if (instance == null)
			instance = new OrderInfoDaoImpl();
		return instance;
	}

	public OrderInfoDaoImpl() {
		
	}

	@Override
	public ArrayList<OrderInfoVo> getList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		
		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select oi.orderNo, oi.orderDate , m.memid, m.memNo, p.proName , oi.totalprice , oi.orderComplete , oi.ordercancel\r\n"
					+ "from orderinfo oi, regmember m, orderhasproduct op, product p\r\n"
					+ "where oi.memno = m.memno\r\n"
					+ "and oi.orderno = op.orderno\r\n"
					+ "and op.prono = p.prono";
			
			pstmt = conn.prepareStatement(query);
			
			
			
			System.out.println(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				
				System.out.println("작동");
				int no = rs.getInt("orderNo");
				String date = rs.getString("orderDate");
				String memid = rs.getString("memid");
				int memno = rs.getInt("memNo");
				String proName = rs.getString("proName");
				int price = rs.getInt("totalPrice");
				int ordercomplete = rs.getInt("orderComplete");
				int cancel = rs.getInt("ordercancel");
				System.out.println("아이디는 " + memid);
				
				OrderInfoVo vo = new OrderInfoVo(no, date,memid,memno, proName,price,ordercomplete,cancel);
				list.add(vo);
				
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		
		return list;
		
	}
	
	public ArrayList<OrderInfoVo> getList1() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		
		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select oi.orderNo, oi.orderDate , m.memid, m.memNo, p.proName ,  oi.totalprice , oi.orderComplete , oi.ordercancel\r\n"
					+ "from orderinfo oi, regmember m, orderhasproduct op, product p\r\n"
					+ "where oi.memno = m.memno\r\n"
					+ "and oi.orderno = op.orderno\r\n"
					+ "and op.prono = p.prono and orderComplete = 0";
			
			pstmt = conn.prepareStatement(query);
			
			
			
			System.out.println(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				
				System.out.println("작동");
				int no = rs.getInt("orderNo");
				String date = rs.getString("orderDate");
				String memid = rs.getString("memid");
				int memno = rs.getInt("memNo");
				String proName = rs.getString("proName");
				int price = rs.getInt("totalPrice");
				int ordercomplete = rs.getInt("orderComplete");
				int cancel = rs.getInt("ordercancel");
				System.out.println("아이디는 " + memid);
				
				OrderInfoVo vo = new OrderInfoVo(no, date,memid,memno, proName,price,ordercomplete,cancel);
				list.add(vo);
				
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		
		return list;
		
	}
	
	public ArrayList<OrderInfoVo> getList2() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		
		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select oi.orderNo, oi.orderDate , m.memid, p.proName ,  oi.totalprice , oi.orderComplete , oi.ordercancel\r\n"
					+ "from orderinfo oi, regmember m, orderhasproduct op, product p\r\n"
					+ "where oi.memno = m.memno\r\n"
					+ "and oi.orderno = op.orderno\r\n"
					+ "and op.prono = p.prono and orderComplete = 2";
			
			pstmt = conn.prepareStatement(query);
			
			
			
			System.out.println(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				
				System.out.println("작동");
				int no = rs.getInt("orderNo");
				String date = rs.getString("orderDate");
				String memid = rs.getString("memid");
				int memno = rs.getInt("memNo");
				String proName = rs.getString("proName");
				int price = rs.getInt("totalPrice");
				int ordercomplete = rs.getInt("orderComplete");
				int cancel = rs.getInt("ordercancel");
				System.out.println("아이디는 " + memid);
				
				OrderInfoVo vo = new OrderInfoVo(no, date,memid,memno, proName,price,ordercomplete,cancel);
				list.add(vo);
				
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		
		return list;
		
	}

	public ArrayList<OrderInfoVo> getRecentList(int memNo) {	//해당 멤버의 가장 최근 주문 3개 가져오는 리스트
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		
		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select *\r\n"
					+ "from (select orderdate, totalprice, ordercomplete\r\n"
					+ "from orderinfo\r\n"
					+ "where memno = "+memNo+"\r\n"
					+ "order by orderdate desc)\r\n"
					+ "where rownum <= 3";
			
			pstmt = conn.prepareStatement(query);
			
			System.out.println(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				
				String orderDate = rs.getString("orderDate");
				int totalPrice = rs.getInt("totalPrice");
				int orderComplete = rs.getInt("orderComplete");
				OrderInfoVo vo = new OrderInfoVo(orderDate, totalPrice, orderComplete, memNo);
				list.add(vo);
				
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		
		return list;
	}
	@Override
	public int delete(int orderNo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
	
		try {
			conn = getConnection();
	
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from orderInfo o, orderhasproduct op "
					     + "where o.orderNo = op.orderNo "
					     + "and o.orderNo = ? ";
			pstmt = conn.prepareStatement(query);
	
			pstmt.setInt(1, orderNo);
	
			count = pstmt.executeUpdate();
	
			// 4.결과처리
			System.out.println(count + "건 orderInfo 삭제");
	
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
	
		}
	
		return count;
	}

	@Override
	public int insert(OrderInfoVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into orderInfo values (seq_orderinfo_no.nextval, to_char(sysdate, 'YY-MM-DD HH24:MI') , ?, ? , 0 , 0)";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, vo.getTotalPrice());
			pstmt.setInt(2, vo.getMemNo());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "  건 orderInfo 등록");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return count;
	}

	@Override
	public List<OrderInfoVo> getDetail(int orderNo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		try {
			conn = getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select p.proname a , op.amount b , o.ordercomplete c , o.orderdate d , p.proprice e , p.proNo f "
					+ "from orderhasproduct op , orderinfo o , product p "
					+ "where p.proNo = op.proNo "
					+ "and o.orderno = op.orderno "
					+ "and o.orderNo = " + orderNo ;
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				OrderInfoVo vo = new OrderInfoVo();
				String proName = rs.getString("a");
				int amount = rs.getInt("b");
				int orderComplete = rs.getInt("c");
				String orderDate = rs.getString("d");
				int proPrice = rs.getInt("e");
				int proNo = rs.getInt("f");
				vo.setProName(proName);
				vo.setCount(amount);
				vo.setOrderComplete(orderComplete);
				vo.setOrderDate(orderDate);
				vo.setProPrice(proPrice);
				vo.setProNo(proNo);
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return list;
	}
	

	@Override
	public List<OrderInfoVo> getList(int page , int limit, int memNo , String searchDate1, String searchDate2 ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = getListCount(searchDate1,searchDate2 ,memNo);
		int start = (page - 1) * limit;
		int index = start + 1;
		String sql;
		
		if(searchDate1 == null || searchDate1 == "") {
			sql =     "select o.orderno ,  o.totalprice , o.ordercomplete "
					+ "from orderinfo o "
					+ "where o.memno = " + memNo
					+ " and o.orderCancel = 0 order by o.orderNo desc ";
		}else {
			sql = "select o.orderno ,  o.totalprice , o.orderComplete "
					+ "from orderhasproduct op , orderinfo o, product p , regmember r " + "where op.orderno = o.orderno "
					+ "and op.prono = p.prono "
					+ "and o.memno = r.memno "
					+ "and o.memno = " + memNo
					+ " and o.orderdate BETWEEN "  + searchDate1
					+ " and " + searchDate2
					+ " and o.orderCancel = 0  order by o.orderNo desc";
			
		}
		
		List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		try {
			conn = getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				int orderNo = rs.getInt("orderNo");
				String proName = repProName(orderNo);
				String orderDate = repDate(orderNo);
				int proPrice = rs.getInt("totalprice");
				int count = getProductCount(orderNo);
				int amount = getProductAmount(orderNo);
				int orderComplete = Integer.parseInt(rs.getString("orderComplete"));
				List<OrderInfoVo> list2 = new ArrayList<OrderInfoVo>();
				for(int i =0 ; i <getDetail(orderNo).size(); i++) {
					OrderInfoVo vo4 = getDetail(orderNo).get(i);
					System.out.println("오더넘버:  " +orderNo+"     상품명:    " +  vo4.getProName() +"수량 :  " + vo4.getCount() );
					list2.add(i , vo4);
				}
				OrderInfoVo vo = new OrderInfoVo(orderNo, proName, orderDate, proPrice, orderComplete, count, amount , list2);
				list.add(vo);
				
				if (index < (start + limit) && index <= total_record)
					index++;
				else
					break;
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return list;
	}
	
	public int detailCount(int orderInfono) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rs = null;

		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT COUNT(*) no "
					+ "from orderhasproduct op , orderinfo o, product p , regmember r " 
					+ "where op.orderno = o.orderno "
					+ "and op.prono = p.prono " 
					+ "and o.memno = r.memno " 
					+ " and o.orderCancel = 0 "
					+ "and r.memno = " + orderInfono;
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, orderInfono);
			
			rs = pstmt.executeQuery();

			
			// 4.결과처리
			while (rs.next()) {
				
				
				count = rs.getInt("no");
			
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		return count;

	}
	
public String repProName(int orderInfono) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String repProName = null;

		try {
		  conn = getConnection();
		  
		  
		  
      
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select p.proname "
					     + "from orderhasproduct op , orderinfo o, product p , regmember r "
					     + "where op.orderno = o.orderno "
					     + "and op.prono = p.prono "
						 + "and o.memno = r.memno "
						 + "and o.orderno = ? "
						 + "and rownum = 1 "
						 + "order by p.proprice";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, orderInfono);
			
			rs = pstmt.executeQuery();

			
			// 4.결과처리
			while (rs.next()) {
				repProName = rs.getString("proname");
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		return repProName;

	}
public String repDate(int orderInfono) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String repDate = null;

		try {
		  conn = getConnection();
		  
		  
		  
      
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select o.orderDate "
					     + "from orderhasproduct op , orderinfo o, product p , regmember r "
					     + "where op.orderno = o.orderno "
					     + "and op.prono = p.prono "
						 + "and o.memno = r.memno "
						 + "and o.orderno = ? "
						 + "and rownum = 1 "
						 + "order by p.proprice";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, orderInfono);
			
			rs = pstmt.executeQuery();

			
			// 4.결과처리
			while (rs.next()) {

				repDate = rs.getString("orderDate");
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		return repDate;

	}
	
	
@Override
public int getListCount(String searchDate1, String searchDate2, int memNo) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql;
	int x = 0;
	if (searchDate1 == null && searchDate2 == null)
		sql = "select  count(*) from orderinfo where memNo = " + memNo + " and ordercancel = 0 "	;
	else
		sql = "SELECT   count(*) FROM (select b.no, b.title, b.hit, b.reg_date, b.user_no, u.name "
				+ "from board b join users u "
				+ "on b.user_no = u.no "
				+ "where "+searchDate1+" Like '%"+searchDate2+"%' "
				+ " and o.ordercancel = 0 "		
				+ "order by no desc)";
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next())
			x = rs.getInt(1);
		
	} catch (Exception ex) {
		System.out.println("getListCount() 에러: " + ex);
	} finally {			
		try {				
			if (rs != null)
				rs.close();							
			if (pstmt != null)
				pstmt.close();				
			if (conn != null)
				conn.close();												
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}		
	}		
	return x;

}
	
	public int getProductAmount(int orderNo ) {
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		String sql =  " select  SUM(op.amount) sum "
				 	+ " from orderhasproduct op , orderinfo o, product p , regmember r " 
				    + " where op.orderno = o.orderno " 
				    + " and op.prono = p.prono "
				    + " and o.memno = r.memno "
				    + " and op.orderNo = " + orderNo;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				x = rs.getInt(1);
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {			
			try {				
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();												
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}		
		return x;
	
	} 
	
	public int getProductCount(int orderNo ) {
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		String sql =  " select count(op.amount) sum "
				 	+ " from orderhasproduct op , orderinfo o, product p , regmember r " 
				    + " where op.orderno = o.orderno " 
				    + " and op.prono = p.prono "
				    + " and o.memno = r.memno "
				    + " and op.orderNo = " + orderNo;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				x = rs.getInt(1);
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {			
			try {				
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();												
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}		
		return x;
	
	} 
	public int getOrderNo(int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		String sql =  "select  orderno "
					+ " from (select o.orderno orderno " 
					+ " from orderinfo o "
					+ " where o.memno = "+ memNo
					+ " order by orderdate desc) "
					+ " where  ROWNUM = 1 ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				x = rs.getInt(1);
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {			
			try {				
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();												
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}		
		return x;
	}

	@Override
	public OrderInfoVo recent(int memNo) {
		int orderNo = getOrderNo(memNo);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderInfoVo vo = new OrderInfoVo();
		String x = "";
		String sql =  " select proFileName "
				+ "from product "
				+ "where proname = '" + repProName(orderNo) + "'"; 
				   
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				x = rs.getString(1);
				vo.setProFileName(x);;
				vo.setOrderNo(orderNo);
				vo.setOrderDate(repDate(orderNo));
				vo.setProName(repProName(orderNo));
				vo.setTotalPrice(getTotalPrice(orderNo));
				vo.setCount(getProductCount(orderNo));
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {			
			try {				
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();												
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}		
		return vo;
	
	} 
	public int getTotalPrice(int orderNo ) {
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		String sql =  " select totalprice from orderinfo where orderno = " + orderNo;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				x = rs.getInt(1);
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {			
			try {				
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();												
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}		
		return x;
	
	}

	@Override
	public int change(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			  conn = getConnection();

				// 3. SQL문 준비 / 바인딩 / 실행
			  	String query = "update orderinfo set orderComplete = 2 where orderNo = ?";
				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, no);

				count = pstmt.executeUpdate();

				// 4.결과처리
				System.out.println(count + "건 증가");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				// 5. 자원정리
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}

			}

			return count;
	} 

	
}
