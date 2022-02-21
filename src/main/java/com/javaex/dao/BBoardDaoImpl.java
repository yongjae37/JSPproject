package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BBoardVo;

public class BBoardDaoImpl implements BBoardDao {
	
	private static BBoardDao instance;
	
	public BBoardDaoImpl()	{
		
	}
	public static BBoardDao getInstance() {
		if(instance == null) {
			instance = new BBoardDaoImpl();
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


	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query = "select count(*) from bboard b order by bno desc";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
		} catch (Exception e){
			System.out.println("getListCount error: " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return count;
	}
	
	public List<BBoardVo> getList(int page, int limit) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BBoardVo> list = new ArrayList<BBoardVo>();

		int total_record = getListCount();
		int start = (page - 1) * limit;
		int index = start + 1;
		
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select bno, title, content, regDate " + " from bboard b" + " order by bno desc";

			pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.absolute(index)) {
				BBoardVo vo = new BBoardVo();
				vo.setbNo(rs.getInt("bNo"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getString("regDate"));

				list.add(vo);
				if (index < (start + limit) && index <= total_record)
					index++;
			
				else 
					break;
			}

		} catch (SQLException e) {
			System.out.println("getList error:" + e);
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
				throw new RuntimeException(e.getMessage());
			}

		}

		return list;

	}
	
	
	public List<BBoardVo> getList2() {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BBoardVo> list = new ArrayList<BBoardVo>();

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from (\r\n"
					+ "select * from bboard order by bNo desc)\r\n"
					+ "where rownum <= 3";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				int bNo = rs.getInt("bNo");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regDate = rs.getString("regDate");

				BBoardVo vo = new BBoardVo(bNo, title, content, regDate);
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
	

	public BBoardVo getBoard(int bNo) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BBoardVo BBoardVo = null;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select bno, title, content, regDate " + " from bboard" + " where bno = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);

			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regDate = rs.getString("regDate");

				BBoardVo = new BBoardVo(bNo, title, content, regDate);
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
		System.out.println(BBoardVo);
		return BBoardVo;

	}

	public int insert(BBoardVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection();

			System.out.println("vo.title : [" + vo.getTitle() + "]");
			System.out.println("vo.content : [" + vo.getContent() + "]");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into bboard values (seq_bboard_no.nextval, ?, ?, to_char(sysdate, 'YY-MM-DD HH24:MI'))";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());

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

//
	public int delete(int bNo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from bboard where bNo = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, bNo);

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
//
//	public int update(BoardVo vo) {
//		// 0. import java.sql.*;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int count = 0;
//
//		try {
//			conn = getConnection();
//
//			// 3. SQL문 준비 / 바인딩 / 실행
//			String query = "update board set title = ?, content = ? where no = ? ";
//			pstmt = conn.prepareStatement(query);
//
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContent());
//			pstmt.setInt(3, vo.getNo());
//
//			count = pstmt.executeUpdate();
//
//			// 4.결과처리
//			System.out.println(count + "건 수정");
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			// 5. 자원정리
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error:" + e);
//			}
//
//		}
//
//		return count;
//	}
//
//	
//	public List<BoardVo> search() {
//
//		// 0. import java.sql.*;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<BoardVo> list = new ArrayList<BoardVo>();
//
//		try {
//			conn = getConnection();
//
//			// 3. SQL문 준비 / 바인딩 / 실행
//			String query = "select b.no, b.title, b.hit, b.reg_date, b.user_no, u.name " + " from board b, users u "
//					+ " where b.user_no = u.no " + " order by no desc";
//
//			pstmt = conn.prepareStatement(query);
//
//			rs = pstmt.executeQuery();
//			// 4.결과처리
//			while (rs.next()) {
//				int no = rs.getInt("no");
//				String title = rs.getString("title");
//				int hit = rs.getInt("hit");
//				String regDate = rs.getString("reg_date");
//				int userNo = rs.getInt("user_no");
//				String userName = rs.getString("name");
//
//				BoardVo vo = new BoardVo(no, title, hit, regDate, userNo, userName);
//				list.add(vo);
//			}
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			// 5. 자원정리
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error:" + e);
//			}
//
//		}
//
//		return list;
//
//	}

	@Override
	public BBoardVo prev(int bNo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BBoardVo BBoardVo = null;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT prev_no, prev_title, prev_content, prev_regDate\r\n"
					+ "FROM(\r\n"
					+ "    SELECT  bno, title, content, regDate,\r\n"
					+ "            LAG(bNo) OVER (ORDER BY bNo) prev_no,\r\n"
					+ "            LAG(title) OVER (ORDER BY bNo) prev_title,\r\n"
					+ "            LAG(content) OVER (ORDER BY bNo) prev_content,\r\n"
					+ "            LAG(regDate) OVER (ORDER BY bNo) prev_regDate\r\n"
					+ "    FROM    bboard\r\n"
					+ "    ORDER BY bNo\r\n"
					+ "    )\r\n"
					+ "WHERE bNo = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);

			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				int prev_no = rs.getInt("prev_no");
				String prev_title = rs.getString("prev_title");
				String prev_regDate = rs.getString("prev_regDate");

				BBoardVo = new BBoardVo(prev_no, prev_title, prev_regDate);
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
		System.out.println(BBoardVo);
		return BBoardVo;
	}

	@Override
	public BBoardVo after(int bNo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BBoardVo BBoardVo = null;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT after_no, after_title, after_content, after_regDate\r\n"
					+ "FROM(\r\n"
					+ "    SELECT  bNo, title, content, regDate,\r\n"
					+ "            LEAD(bNo) OVER (ORDER BY bNo) after_no,\r\n"
					+ "            LEAD(title) OVER (ORDER BY bNo) after_title,\r\n"
					+ "            LEAD(content) OVER (ORDER BY bNo) after_content,\r\n"
					+ "            LEAD(regDate) OVER (ORDER BY bNo) after_regDate\r\n"
					+ "    FROM    bboard\r\n"
					+ "    ORDER BY bNo\r\n"
					+ "    )\r\n"
					+ "WHERE bNo = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);

			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				int after_no = rs.getInt("after_no");
				String after_title = rs.getString("after_title");
				String after_regDate = rs.getString("after_regDate");
				
				BBoardVo = new BBoardVo(after_no, after_title, after_regDate);
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
		System.out.println(BBoardVo);
		return BBoardVo;
	}

}
