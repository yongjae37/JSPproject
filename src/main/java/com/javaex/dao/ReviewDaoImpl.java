package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.ProductVo;
import com.javaex.vo.ReviewVo;

public class ReviewDaoImpl implements ReviewDao{
	
	private static ReviewDao instance;
	public ReviewDaoImpl() {
		
	}
	
	public static ReviewDao getInstance() {
		if(instance == null) {
			instance = new ReviewDaoImpl();
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
	public int reviewExists(int proNo, int memNo) {  //해당 멤버의 해당 상품에 대한 avail한 리뷰가 있는지 확인
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		int exists = 0;
		try {
			String query = "SELECT count(*)\r\n"
					+ "from review \r\n"
					+ "where proNo = "+proNo+"\r\n"
					+ "and revavail = 1\r\n"
					+ "and memNo = "+memNo+"\r\n";
			
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				count = rs.getInt(1);
			if (count == 1) {
				exists = 1;
			} else { 
				exists = 0;
			}
		} catch (Exception e) {
			System.out.println("updateAvail error:" + e);
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
		return exists;
	}
	
	@Override
	public int writeAvail(int proNo, int memNo) { //리뷰 작성 가능한지 여부 (해당 멤버가 해당 상품을 구매했는지) 확인
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		int avail = 0;
		try {
			String query = "select count(*)\r\n"
					+ "from orderhasproduct op, orderinfo o\r\n"
					+ "where op.orderno = o.orderno\r\n"
					+ "and op.prono = "+proNo+"\r\n"
					+ "and o.memno = " + memNo;  
			
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				count = rs.getInt(1);
			
			if (count > 0) { //0보다 크면 구매O
				if (reviewExists(proNo, memNo) == 1) { 
					avail = 0;
				} else {
					avail = 1;
				}
			} else {
				avail = 0;
			}
		} catch (Exception e) {
			System.out.println("writeAvail error:" + e);
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
		return avail;
	}


	public int updateAvail(int memNo, int revNo) { //리뷰 수정 가능한지 여부 (해당 멤버가 해당 리뷰를 작성했는지) 확인
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		int avail = 0;
		try {
			String query = "SELECT count(*)\r\n"
					+ "from review\r\n"
					+ "where memNo = "+memNo+"\r\n"
					+ "and revNo = " + revNo;
			
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			//수정 할 수 있는 사람 == 리뷰 이미 작성한 사람 == 리뷰 작성 못하는 사람
			if (rs.next())
				count = rs.getInt(1);
			if (count == 1) { //수정 가능
				avail = 1;
			} else { //수정 불가
				avail = 0;
			}
		} catch (Exception e) {
			System.out.println("updateAvail error:" + e);
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
		return avail;
	}
	
	@Override
	public int insert(ReviewVo vo) {	//리뷰 db에 입력
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;

		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into review\r\n"
					+ "values (seq_review_no.nextval, ?, ?, ?, ?, to_char(sysdate, 'YY-MM-DD HH24:MI'), ?, 0, ?, ?, 1)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getMemNo());
			pstmt.setInt(2, vo.getProNo());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setInt(5, vo.getRate());
			pstmt.setString(6, vo.getFile1());
			pstmt.setString(7, vo.getFile2());
			
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
	public int update(ReviewVo vo) {	//리뷰 수정 db에 입력
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update review " +
					 " set title = ?, content = ?,  rate = ?, file1 = ?, file2 = ?" +
					 " where revNo = ?";            
			
			pstmt = conn.prepareStatement(query);

			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getRate());
	        pstmt.setString(4, vo.getFile1());
			pstmt.setString(5, vo.getFile2());
			pstmt.setInt(6, vo.getRevNo());
			
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
	public int delete(int revNo) {  //db에 해당 리뷰의 revAvail 값을 0으로 수정
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
		  conn = getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update review set revAvail = 0 where revNo="+revNo;

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
	public List<ReviewVo> getList(int page, int limit, int proNo, String orderBy) { //리뷰 리스트 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = getListCount(proNo);
		int start = (page - 1) * limit;
		int index = start + 1;
		String sql;
		if (orderBy == null || "".equals(orderBy)) {
			orderBy = "revDate";
		}
		
		List<ReviewVo> list = new ArrayList<ReviewVo>();
			
		if (proNo == -1) {
			sql = "select r.* , p.proname , m.memid from review r, product p, regmember m \r\n"
					+ "where r.proNo = p.proNo\r\n"
					+ "and r.memno = m.memno\r\n"
					+ "and r.revavail = 1\r\n"
					+ " order by r." + orderBy +" desc";

		}
		else { 
			sql = "select r.* , p.proname , m.memid from review r, product p, regmember m \r\n"
					+ "where r.proNo = p.proNo\r\n"
					+ "and r.revavail = 1\r\n"
					+ "and r.memno = m.memno\r\n"
					+ "and r.prono = " + proNo 
					+ " order by r." + orderBy +" desc";

		}
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				ReviewVo vo = new ReviewVo();
				vo.setRevNo(rs.getInt("RevNo"));
				vo.setMemNo(rs.getInt("MemNo"));
				vo.setProNo(rs.getInt("proNo"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRevDate(rs.getString("REVDATE"));
				vo.setRate(rs.getInt("rate"));
				vo.setRevHit(rs.getInt("revhit"));
				vo.setFile1(rs.getString("file1"));
				vo.setFile2(rs.getString("file2"));
				vo.setProName(rs.getString("proName"));
				
				vo.setMemId(rs.getString("memid").substring(0,2) + "***"); 
			
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

	@Override
	public int getListCount(int proNo) {	//리뷰 갯수 세기 (for pagination)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query;
		
		
		if(proNo == -1) {
			query = "select count(*) from review where revavail = 1";
		}else {
			query = "select count(*) from review where revavail = 1 and proNo = " + proNo;
		}
		
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("리스트 카운트 에러 " + ex);
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
	public void updateHit(int revNo) {	//조회수 1씩 증가
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = "select revHit from review where revNo = " + revNo;
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			int hit = 0;

			if (rs.next())
				hit = rs.getInt("hit") + 1;
		
			sql = "update review set revHit=? where revNo= " + revNo;
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, hit);

			pstmt.executeUpdate();
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
	
	public float rateAvg(int proNum ) {	//평점 평균 구하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		float avg = 0;
		try {
			conn = getConnection();

			String sql = "select avg(rate)  from review where prono = " + proNum;
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			

			if (rs.next())
				avg = rs.getFloat("avg(rate)");
				
		} catch (Exception ex) {
			System.out.println("rateAvg() : " + ex);
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
		return avg;
	}
	
	@Override
	public ReviewVo getReview(int revNo) {	//특정 리뷰 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVo vo = new ReviewVo();
		
		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select r.memNo, r.proNo, r.title, r.content, r.revDate, r.rate, r.revHit, r.file1, r.file2, p.proName "
					+ "from review r, product p "
					+ "where revNo = " +revNo
					+ "and r.prono = p.prono";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			if (rs.next()) {
				int memNo = rs.getInt("memNo");
				
				int proNo = rs.getInt("proNo");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String revDate = rs.getString("revDate");
				int rate = rs.getInt("rate");
				int revHit = rs.getInt("revHit");
				String file1 = rs.getString("file1");
				String file2 = rs.getString("file2");
				String proName = rs.getString("proName");
				
				vo = new ReviewVo();
				
				vo.setMemNo(memNo);
				vo.setProNo(proNo);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRevDate(revDate);
				vo.setRate(rate);
				vo.setRevHit(revHit);
				vo.setFile1(file1);
				vo.setFile2(file2);
				vo.setProName(proName);
				
			}

		} catch (SQLException e) {
			System.out.println("getReview error:" + e);
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
				System.out.println("getReview error:" + e);
			}

		}

		return vo;
	}
	
	@Override
	public List<ReviewVo> getPrivateList(int page, int limit, int memNo) {	//해당 멤버의 리뷰 전체 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = privateReviewCount(memNo);
		int start = (page - 1) * limit;
		int index = start + 1;
		String sql = "select r.* , p.proname "
				+ "from review r, product p "
				+ "where memno =  " + memNo
				+ "and revavail = 1  "
				+ "and r.prono = p.prono "
				+ "order by revdate desc";
		
		
		List<ReviewVo> list = new ArrayList<ReviewVo>();
			
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				ReviewVo vo = new ReviewVo();
				vo.setRevNo(rs.getInt("RevNo"));
				vo.setMemNo(rs.getInt("MemNo"));
				vo.setProNo(rs.getInt("proNo"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRevDate(rs.getString("REVDATE"));
				vo.setRate(rs.getInt("rate"));
				vo.setRevHit(rs.getInt("revhit"));
				vo.setFile1(rs.getString("file1"));
				vo.setFile2(rs.getString("file2"));
				vo.setProName(rs.getString("proName"));
				
			
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
	
	@Override
	public int privateReviewCount(int memNo) {	//해당 멤버의 리뷰 갯수 (for pagination)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query = "select count(*) from review where memNo = " + memNo;
		try {
			
			
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("리스트 카운트 에러 " + ex);
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
	
	public int getAllListCount() {	//모든 리뷰 갯수 (for manager)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query = "select count(*) from review where revavail = 1";
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("getAllListCount error: " + ex);
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
	
	public List<ReviewVo> getAllList(int page, int limit) {	//모든 리뷰 리스트 (for manager)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = getAllListCount();
		int start = (page - 1) * limit;
		int index = start + 1;
		
		String sql = "select r.*, m.memid, p.proname\r\n"
				+ "from review r, regmember m, product p\r\n"
				+ "where revavail = 1\r\n"
				+ "and r.memno = m.memno\r\n"
				+ "and r.prono = p.prono  \r\n"
				+ "order by revdate desc";
				
		List<ReviewVo> list = new ArrayList<ReviewVo>();
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				ReviewVo vo = new ReviewVo();
				vo.setRevNo(rs.getInt("revNo"));
				vo.setProName(rs.getString("proName"));
				vo.setTitle(rs.getString("title"));
				vo.setMemId(rs.getString("memId"));
				vo.setRate(rs.getInt("rate"));
				vo.setProNo(rs.getInt("proNo"));

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
}
