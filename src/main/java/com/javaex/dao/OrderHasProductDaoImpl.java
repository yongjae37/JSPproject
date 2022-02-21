package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class OrderHasProductDaoImpl implements OrderHasProductDao {

	
	
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
	public int insert(int orderNo, int proNo , int amount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into orderhasproduct values ( ?, ? , ? )";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, proNo);
			pstmt.setInt(3, amount);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "  orderhasproduct 등록");

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
	public int delete(int orderNo) {
		// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				int count = 0;
			
				try {
					conn = getConnection();
			
					// 3. SQL문 준비 / 바인딩 / 실행
					String query = "delete from orderhasproduct "
							     + "where orderNo = ? ";
					pstmt = conn.prepareStatement(query);
			
					pstmt.setInt(1, orderNo);
			
					count = pstmt.executeUpdate();
			
					// 4.결과처리
					System.out.println(count + "건 orderhasproduct 삭제");
			
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
