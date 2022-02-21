package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.javaex.vo.AnswerVo;


public class AnswerDaoImpl implements AnswerDao{
	
	private static AnswerDao instance;
	public AnswerDaoImpl() {
		
	}
	public static AnswerDao getInstance() {
		if (instance == null) {
			instance = new AnswerDaoImpl();
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
	public int insert(AnswerVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;

		try {
		  conn = getConnection();
		  
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into answer values (seq_answer_no.nextval, ?, ?, to_char(sysdate, 'YY-MM-DD HH24:MI'), ?, 1)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getqNo());
			pstmt.setInt(2, vo.getMemNo());
			pstmt.setString(3, vo.getaContent());
		
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			System.out.println("insert error:" + e);
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
				System.out.println("insert error:" + e);
			}

		}

		return count;
	}

	//aavail 0으로 해서 삭제처리
	@Override
	public int delete(int aNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update answer set aavail = 0 where ano = " + aNo;
			pstmt = conn.prepareStatement(query);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("delete error:" + e);
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
				System.out.println("delete error:" + e);
			}

		}

		return count;
	}

	@Override
	public AnswerVo getAnswer(int aNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AnswerVo vo = new AnswerVo();

		try {
		  conn = getConnection();
		  
		  
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select adate, acontent from answer where ano ="+aNo;
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String aDate = rs.getString("aDate");
				String aContent = rs.getString("aContent");
				
				vo = new AnswerVo(aDate, aContent);
				
			}
		} catch (SQLException e) {
			System.out.println("insert error:" + e);
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
				System.out.println("insert error:" + e);
			}

		}

		return vo;
	}
	

	
}
