package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javaex.vo.QnaAnswerVo;
import com.javaex.vo.QnaBoardVo;

public class QnaAnswerDaoImpl implements QnaAnswerDao {

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
	public ArrayList<QnaAnswerVo> getAnsList(int qnaNo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaAnswerVo> list = new ArrayList<QnaAnswerVo>();
		 
		 try {
			 conn = getConnection();
			 
			String query = "select qa.* "
					+ "from qnaanswer qa, qnaboard qb "
					+ "where qa.qnano = qb.qnano "
					+ "and qa.qnano = ? and qa.answerck = 1 ";

			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, qnaNo);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 QnaAnswerVo vo = new QnaAnswerVo();
				 vo.setAnsNo(rs.getInt("ansno"));
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setAnswer(rs.getString("answer"));
				 vo.setRegDate(rs.getString("regdate"));
				 vo.setAnswerCk(rs.getInt("answerck"));
				 
				 list.add(vo);
			 }
		 } catch (SQLException e) {
			 System.out.println("error:" + e);
		 }finally {
			 try {
				 if(pstmt != null) pstmt.close();
				 if(conn != null) conn.close();
			 } catch (SQLException e) {
				 System.out.println("error:" + e);
				 
			 }
		 }
		
		return list;
		
	}

	@Override
	public int insertAns(QnaAnswerVo vo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "insert into qnaanswer values(seq_qnaanswer_no.nextval, ?, ?, ?, sysdate, 1 )";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setInt(1, vo.getQnaNo());
			 pstmt.setInt(2, vo.getMemNo());
			 pstmt.setString(3, vo.getAnswer());
			 
			 count = pstmt.executeUpdate();
			 
			 System.out.println(count + "건 등록");
			 		 
		 } catch (SQLException e) {
			 System.out.println("error:" + e);
		 }finally {
			 try {
				 if(pstmt != null) pstmt.close();
				 if(conn != null) conn.close();
			 } catch (SQLException e) {
				 System.out.println("error:" + e);
				 
			 }
		 }
		return count;
	}

	@Override
	public int deleteAns(int ansNo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "update qnaanswer "
			 		+ "set answerck=0 "
			 		+ "where ansno = ? ";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setInt(1, ansNo);
			 
			 count = pstmt.executeUpdate();
			 
			 System.out.println(count + "건 삭제");
			 
		 } catch (SQLException e) {
			 System.out.println("error:" + e);
		 }finally {
			 try {
				 if(pstmt != null) pstmt.close();
				 if(conn != null) conn.close();
			 } catch (SQLException e) {
				 System.out.println("error:" + e);
				 
			 }
		 }
		return count;
	}

}

