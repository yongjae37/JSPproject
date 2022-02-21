package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.ProductVo;

public class ProductDaoImpl implements ProductDao{
	
	private static ProductDao instance;
	public ProductDaoImpl() {
		
	}
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDaoImpl();
		}
		return instance;
	}
	

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
	
	@Override
	public int insert(ProductVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		
		try {
		  conn = getConnection();
		  
		  System.out.println("insert 상품이름 : ["+vo.getProName()+"]");
		  
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into product\r\n"
					+ "values (seq_product_no.nextval, ?, ?, ?, ?, ?, 1, ?, to_char(sysdate, 'YY-MM-DD HH24:MI'), 0)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getProName());
			pstmt.setString(2, vo.getProCateg());
			pstmt.setInt(3, vo.getProStock());
			pstmt.setInt(4, vo.getProPrice());
			pstmt.setString(5, vo.getProDesc());
			pstmt.setString(6, vo.getProFileName());
			
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

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
	public int delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update product set proOnSale = 0 where proNo = "+ num;
			pstmt = conn.prepareStatement(query);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

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
	public int update(ProductVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update product\r\n"
					+ "set proName = ?, proCateg = ?, proStock = ?, proPrice = ?, proDesc = ?, proOnSale = ?, proFileName = ?\r\n"
					+ "where proNo = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getProName());
			pstmt.setString(2, vo.getProCateg());
			pstmt.setInt(3, vo.getProStock());
			pstmt.setInt(4, vo.getProPrice());
			pstmt.setString(5, vo.getProDesc());
			pstmt.setInt(6, vo.getProOnSale());
			pstmt.setString(7, vo.getProFileName());
			pstmt.setInt(8, vo.getProNo());
			
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정");

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
	public ProductVo getProduct(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVo vo = new ProductVo();
		
		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from product where proNo = "+num;
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			if (rs.next()) {
				int proNo = rs.getInt("proNo");
				String proName = rs.getString("proName");
				String proCateg = rs.getString("proCateg");
				int proStock = rs.getInt("proStock");
				int proPrice = rs.getInt("proPrice");
				String proDesc = rs.getString("proDesc");
				int proOnSale = rs.getInt("proOnSale");
				String proFileName = rs.getString("proFileName");
				String proDate = rs.getString("proDate");
				int proHit = rs.getInt("proHit");

				vo = new ProductVo();
				vo.setProNo(proNo);
				vo.setProName(proName);
				vo.setProCateg(proCateg);
				vo.setProStock(proStock);
				vo.setProPrice(proPrice);
				vo.setProDesc(proDesc);
				vo.setProOnSale(proOnSale);
				vo.setProFileName(proFileName);
				vo.setProDate(proDate);
				vo.setProHit(proHit);
				
			}

		} catch (SQLException e) {
			System.out.println("getProduct error:" + e);
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
				System.out.println("getProduct error:" + e);
			}

		}

		return vo;
	}

	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String query;
				
		try {
			query = "select count(*) from product where proOnSale = 1";

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("getListCount() : " + ex);
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
		return count;
	}
	
	public int getListCount(String searchfrom, String kwd, String categ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query;
		
		
		if(searchfrom == null || kwd == null) {
			if (categ == null)
				query = "select count(*) from product where proOnSale = 1";
			else
				query = "select count(*) from product where proOnSale = 1 and proCateg='"+categ+"'";
		}else {
			if (categ == null)
				query = "select count(prono) from product where proOnSale = 1 and instr(" + searchfrom + " , '" + kwd + "') > 0";
			else
				query = "select count(prono) from product where proOnSale = 1 and instr(" + searchfrom + " , '" + kwd + "') >0 and proCateg = "+categ;
		}
		
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("getListCount(searchfrom, kwd, categ) : " + ex);
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
		return count;
	}
	
	public List<ProductVo> getList(int page, int limit , String searchFrom, String kwd, String orderBy, String categ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = getListCount(searchFrom, kwd, categ);
		ReviewDao rdao = new ReviewDaoImpl();
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		int start = (page - 1) * limit;
		int index = start + 1;
		String sql;
		if (orderBy == null || "".equals(orderBy)) {
			orderBy = "proDate";
		}

		if (searchFrom == null ||kwd == null) {
			if (categ == null)
				sql = "select * from product where proOnsale = 1 order by proDate desc";
			else
				sql = "select * from product where proCateg = '"+categ+"' and proOnSale = 1 order by proDate desc";
		}
		else { 
			if (categ == null)
				sql = "select * from product\r\n"
						+ "where instr("+searchFrom+", '"+kwd+"') > 0\r\n"
						+ " and proOnSale = 1"
						+ "order by "+orderBy;
			else
				sql = "select * from product\r\n"
						+ "where instr("+searchFrom+", '"+kwd+"') > 0\r\n"
						+ "and proCateg = '"+categ
						+ " and proOnSale = 1"
						+ "' order by "+orderBy;

		}
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				ProductVo vo = new ProductVo();
				vo.setProNo(rs.getInt("proNo"));
				vo.setProName(rs.getString("proName"));
				vo.setProCateg(rs.getString("proCateg"));
				vo.setProStock(rs.getInt("proStock"));
				vo.setProPrice(rs.getInt("proPrice"));
				vo.setProDesc(rs.getString("proDesc"));
				vo.setProOnSale(rs.getInt("proOnSale"));
				vo.setProFileName(rs.getString("proFileName"));
				vo.setProDate(rs.getString("proDate"));
				vo.setProHit(rs.getInt("proHit"));
				
				vo.setProRate(rdao.rateAvg(vo.getProNo()));
				list.add(vo);
				if (index < (start + limit) && index <= total_record)
					index++;
			
				else 
					break;
			}
			
		} catch (Exception ex) {
			System.out.println("getList() error : " + ex);
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
		
		return list;
	}
	
	public int getAdvListCount(String onSale, String categ, String name, String startPrice, String endPrice) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String query;
		if (startPrice.equals("") || startPrice == null) {
			if (endPrice.equals("") || endPrice == null) {
				query = "select count(*)\r\n"
						+ "from product \r\n"
						+ "where proOnSale like '%" + onSale + "%'\r\n"
						+ "and proCateg like '%" + categ +"%'\r\n"
						+ "and proName like '%"+ name +"%'";
			}
			else {
				query = "select count(*)\r\n"
						+ "from product \r\n"
						+ "where proOnSale like '%"+ onSale +"%'\r\n"
						+ "and proCateg like '%"+ categ +"%'\r\n"
						+ "and proName like '%"+ name +"%'\r\n"
						+ "and proPrice <= "+endPrice;
			}
		} else {
			if (endPrice.equals("") || endPrice == null) {
				query = "select count(*)\r\n"
						+ "from product \r\n"
						+ "where proOnSale like '%"+ onSale +"%'\r\n"
						+ "and proCateg like '%"+ categ +"%'\r\n"
						+ "and proName like '%"+ name +"%'\r\n"
						+ "and proPrice >= "+startPrice;
			}
			else {
				query = "select count(*) \r\n"
				+ "from product \r\n"
				+ "where proOnSale like '%"+onSale+"%'\r\n"
				+ "and proCateg like '%"+categ+"%'\r\n"
				+ "and proName like '%"+name+"%'\r\n"
				+ "and proPrice BETWEEN "+startPrice+" AND "+endPrice+"\r\n";
			}
		}
		
		try {

			conn = getConnection();
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			
		} catch (Exception ex)	{
			System.out.println("getListCount(String onSale, String categ, String name, String startPrice, String endPrice) : " + ex);
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
		return count;
	}
	
	public List<ProductVo> getAdvList(int page, int limit , String onSale, String categ, String name, String startPrice, String endPrice){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = getAdvListCount(onSale, categ, name, startPrice, endPrice);
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		int start = (page - 1) * limit;
		int index = start + 1;
		
		String query;
		if (startPrice.equals("") || startPrice == null) {
			if (endPrice.equals("") || endPrice == null) {
				query = "select *\r\n"
						+ "from product \r\n"
						+ "where proOnSale like '%" + onSale + "%'\r\n"
						+ "and proCateg like '%" + categ +"%'\r\n"
						+ "and proName like '%"+ name +"%'";
			}
			else {
				query = "select *\r\n"
						+ "from product \r\n"
						+ "where proOnSale like '%"+ onSale +"%'\r\n"
						+ "and proCateg like '%"+ categ +"%'\r\n"
						+ "and proName like '%"+ name +"%'\r\n"
						+ "and proPrice <= "+endPrice;
			}
		} else {
			if (endPrice.equals("") || endPrice == null) {
				query = "select *\r\n"
						+ "from product \r\n"
						+ "where proOnSale like '%"+ onSale +"%'\r\n"
						+ "and proCateg like '%"+ categ +"%'\r\n"
						+ "and proName like '%"+ name +"%'\r\n"
						+ "and proPrice >= "+startPrice;
			}
			else {
				query = "select *\r\n"
				+ "from product \r\n"
				+ "where proOnSale like '%"+onSale+"%'\r\n"
				+ "and proCateg like '%"+categ+"%'\r\n"
				+ "and proName like '%"+name+"%'\r\n"
				+ "and proPrice BETWEEN "+startPrice+" AND "+endPrice+"\r\n";
			}
		}
		
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				ProductVo vo = new ProductVo();
				vo.setProNo(rs.getInt("proNo"));
				vo.setProName(rs.getString("proName"));
				vo.setProCateg(rs.getString("proCateg"));
				vo.setProStock(rs.getInt("proStock"));
				vo.setProPrice(rs.getInt("proPrice"));
				vo.setProDesc(rs.getString("proDesc"));
				vo.setProOnSale(rs.getInt("proOnSale"));
				vo.setProFileName(rs.getString("proFileName"));
				vo.setProDate(rs.getString("proDate"));
				vo.setProHit(rs.getInt("proHit"));


				list.add(vo);
				if (index < (start + limit) && index <= total_record)
					index++;
			
				else 
					break;
			}
			
		} catch (Exception ex) {
			System.out.println("getAdvList() error : " + ex);
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
		
		return list;
	}
	
	public void updateHit(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = "select proHit from product where proNo = " +num;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int hit = 0;

			if (rs.next()) {
				hit = rs.getInt("proHit") + 1;
				System.out.println("조회수 늘어남");
			}
			sql = "update product set proHit= "+hit+" where proNo= "+num;
			pstmt = conn.prepareStatement(sql);		
			pstmt.executeUpdate();
			System.out.println(hit);
		} catch (Exception ex) {
			System.out.println("updateHit() : " + ex);
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
		
	}
	@Override
	public int stockCheck(int proNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String query;
				
		try {
			query = "select prostock from product where prono = " + proNo;
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("재고확인 에러 : " + ex);
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
		return count;
	}
	
	@Override
	public ArrayList<ProductVo> getList() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		
		try {
		  conn = getConnection();

		
			String query = "select * from product";
			
			pstmt = conn.prepareStatement(query);
			
			
			
			System.out.println(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				
				System.out.println("작동");
				String name = rs.getString("proName");
				String file = rs.getString("profileName");
				
				
				ProductVo vo = new ProductVo();
				vo.setProName(name);
				vo.setProFileName(file);
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
	public ArrayList<ProductVo> recommend(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		
		try {
		  conn = getConnection();

		
			String query = "SELECT p.proname, p.profileName FROM product p, (SELECT b.procateg FROM (SELECT a.procateg, COUNT(a.procateg) FROM (select p.procateg from orderinfo o, regmember r, product p, orderhasproduct op \r\n"
					+ "where r.memno = o.memno and o.orderno = op.orderno and op.prono = p.prono and r.memno = " + id + ") a\r\n"
					+ "GROUP BY a.procateg\r\n"
					+ "HAVING COUNT(a.procateg) > 1 order by COUNT(a.procateg) desc) b WHERE ROWNUM = 1) c where p.procateg = c.procateg";
			
			pstmt = conn.prepareStatement(query);
			
			
			
			System.out.println(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				
				System.out.println("작동");
				String name = rs.getString("proname");
				String file = rs.getString("profileName");
				System.out.println("영화 이름: " + name);
				
				
				ProductVo vo = new ProductVo();
				vo.setProName(name);
				vo.setProFileName(file);
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
	
}

