package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.ShippingVo;

public class ShippingDaoImpl implements ShippingDao {
	
	
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
	public int insert(ShippingVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;

		try {
		  conn = getConnection();
		  
		  
		  
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into Shipping "
					+ "values (? , ?, ?, ? , ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getOrderNo());
			pstmt.setString(2, vo.getsName());
			pstmt.setString(3, vo.getsPostc());
			pstmt.setString(4, vo.getsDoro());
			pstmt.setString(5, vo.getsJibun());
			pstmt.setString(6, vo.getsAdd());
			pstmt.setString(7, vo.getsPhone());
			pstmt.setString(8, vo.getsSps());
			
						
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


	@Override
	public int count1(int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select  count(*) from orderInfo where memNo = " + memNo + " and orderComplete = 0";
		int x = 0;
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
	public int count2(int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select  count(*) from orderInfo where memNo = " + memNo + " and orderComplete = 1";
		int x = 0;
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
	public int count3(int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select  count(*) from orderInfo where memNo = " + memNo + " and orderComplete = 2";
		int x = 0;
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

}
