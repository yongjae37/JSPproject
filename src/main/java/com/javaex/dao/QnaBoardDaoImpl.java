package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.javaex.vo.QnaBoardVo;

public class QnaBoardDaoImpl implements QnaBoardDao {
	
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
	public int insert(QnaBoardVo vo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "insert into qnaboard values (seq_qnaboard_no.nextval, ?, ?, ?, ?, ?, ?, sysdate,?, 1 )";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setInt(1, vo.getMemNo());
			 pstmt.setString(2, vo.getNickname());
			 pstmt.setString(3, vo.getPass());
			 pstmt.setString(4, vo.getTitle());
			 pstmt.setString(5, vo.getType());
			 pstmt.setString(6, vo.getContent());
			 pstmt.setInt(7, vo.getPriv());
			
			 
			 count = pstmt.executeUpdate();
			 
			 System.out.println(count + "건 등록");
			 
			 System.out.println("문의 유형 " + vo.getType());
			 System.out.println("제목 " + vo.getTitle());
			 
			 
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
	public int delete(int no) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "update qnaboard "
			 		+ "set qnabck=0 "
			 		+ "where qnano = ? ";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setInt(1, no);
			 
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

	@Override
	public ArrayList<QnaBoardVo> getList(String type, int page) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
		 
		 try {
			 conn = getConnection();
			 
			 if(type.equals("전체 문의")) {
			 
			String query = "select qna.* "
					+ "from( "
					+ "select rownum num, qn.* "
					+ "from (select q.*, m.memName "
					+ "from qnaboard q, regmember m  "
					+ "where q.memno = m.memno "
					+ "and qnabck=1 "
					+ "order by qnano desc) qn) qna "
					+ "where num between ? and ? ";

			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, 1+(page-1)*5 );
			 pstmt.setInt(2, page*5);
			 
			 } else {
				 
				 String query = "select qna.*  "
				 		+ "from(  "
				 		+ "select rownum num, qn.*  "
				 		+ "from (select q.*, m.memName  "
				 		+ "from qnaboard q, regmember m  "
				 		+ "where q.memno = m.memno  "
				 		+ "and qnabck=1 "
				 		+ "and type like ? "    
				 		+ "order by qnano desc) qn) qna  "
				 		+ "where num between ? and ? ";

					 pstmt = conn.prepareStatement(query);
					 pstmt.setString(1, "%"+type+"%" );
					 pstmt.setInt(2, 1+(page-1)*5 );
					 pstmt.setInt(3, page*5);
				 
			 }
			 			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 QnaBoardVo vo = new QnaBoardVo();
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setMemName(rs.getString("memname"));
				 vo.setNickname(rs.getString("nickname"));
				 vo.setPass(rs.getString("pass"));
				 vo.setTitle(rs.getString("title"));
				 vo.setType(rs.getString("type"));
				 vo.setContent(rs.getString("content"));
				 vo.setRegDate(rs.getString("regdate"));
				 vo.setPriv(rs.getInt("priv"));
				 vo.setQnabCk(rs.getInt("qnabck"));
				 vo.setAnsCnt(getAnsCnt(rs.getInt("qnano")));
				 
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
	public int getBoardCount() {
		return getBoardCount("전체 문의");
	}

	@Override
	public int getBoardCount(String type) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 if(type.equals("전체 문의")) {
			 
			 String query = "select count(qnano) from qnaboard where qnabck =1  ";
			 
			 pstmt = conn.prepareStatement(query);
			 
			 } else {
				 
				 String query = "select count(qnano) from( "
					 		+ "select rownum num, q.* "
					 		+ "from( "
					 		+ "select * from qnaboard "
					 		+ "where type like ? "
					 		+ "and qnabck= 1 "
					 		+ "order by qnano desc)q) ";
					 
					 pstmt = conn.prepareStatement(query);
					 pstmt.setString(1, "%"+type+"%" );			 
				 
			 }
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 count = rs.getInt("count(qnano)");
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
		
		return count;
	}

	@Override
	public QnaBoardVo getBoard(int no) {

		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 QnaBoardVo  vo = new QnaBoardVo();
		 
		 try {
			 conn = getConnection();
			 
			 String query = "select q.*, m.memName "
			 		+ "from qnaboard q, regmember m "
			 		+ "where q.memno = m.memno and qnano = ? ";
			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1,  no);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
			
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setMemName(rs.getString("memname"));
				 vo.setNickname(rs.getString("nickname"));
				 vo.setPass(rs.getString("pass"));
				 vo.setTitle(rs.getString("title"));
				 vo.setType(rs.getString("type"));
				 vo.setContent(rs.getString("content"));
				 vo.setRegDate(rs.getString("regdate"));
				 vo.setPriv(rs.getInt("priv"));
				 vo.setQnabCk(rs.getInt("qnabck"));
			
				// System.out.println(vo.toString());
		 
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
		return vo;
	}

	@Override
	public int update(QnaBoardVo vo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		
		 
		 try {
			 conn = getConnection();
			 
			 String query = "update qnaboard "
			 		+ "set title= ? , type= ?, content = ?, pass = ?,  priv= ? "
			 		+ "where qnano = ? ";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setString(1, vo.getTitle());
			 pstmt.setString(2, vo.getType());
			 pstmt.setString(3, vo.getContent());
			 pstmt.setString(4, vo.getPass());
			 pstmt.setInt(5, vo.getPriv());
			 pstmt.setInt(6, vo.getQnaNo());
			 
			 
			 count = pstmt.executeUpdate();
			 
			 System.out.println(count + "건 수정");
			 
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



	public int getAnsCnt(int qnano) {
			Connection conn = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
			 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
			 int count = 0;
			 
			 try {
				 conn = getConnection();
				 
				 String query = "select count(ansno) "
				 		+ " from qnaanswer"
				 		+ " where qnano =  " + qnano ; 
						 
				 pstmt = conn.prepareStatement(query);
							 
				 rs = pstmt.executeQuery();
				 
				 if(rs.next()) {
					 count = rs.getInt("count(ansno)");
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
			
			return count;
			
		}
	
	

	@Override
	public ArrayList<QnaBoardVo> getNoAnswerList(int page) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
		 
		 try {
			 conn = getConnection();
			 
			 String query = "select qna.* "
			 		+ "from( "
			 		+ "select rownum num, qn.* "
			 		+ "from (select q.*, m.memName "
			 		+ "from noansqna_view q, regmember m "
			 		+ "where q.memno = m.memno "
			 		+ "order by qnano desc) qn) qna "
			 		+ "where num between ? and ? ";

			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, 1+(page-1)*5 );
			 pstmt.setInt(2, page*5);
			 
	 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 QnaBoardVo vo = new QnaBoardVo();
				 
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setMemName(rs.getString("memname"));
				 vo.setNickname(rs.getString("nickname"));
				 vo.setPass(rs.getString("pass"));
				 vo.setTitle(rs.getString("title"));
				 vo.setType(rs.getString("type"));
				 vo.setContent(rs.getString("content"));
				 vo.setRegDate(rs.getString("regdate"));
				 vo.setPriv(rs.getInt("priv"));
				 vo.setQnabCk(rs.getInt("qnabck"));
				 vo.setAnsCnt(getAnsCnt(rs.getInt("qnano")));
				 
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
	public int getNoAnswerCount() {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 String query = "select count(*) from( "
			 		+ "select count(qa.qnano) counta "
			 		+ "from (select q.* from qnaboard q where q.qnabck = 1 ) qb  "
			 		+ "left join qnaanswer qa "
			 		+ "on qa.qnano = qb.qnano "
			 		+ "group by qb.qnano) "
			 		+ "where counta = 0 ";
					 
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			 
			if(rs.next()) {
				 count = rs.getInt("count(*)");
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
		
		return count;
	}


	@Override
	public QnaBoardVo prevQna(int qnaNo) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 QnaBoardVo vo = new QnaBoardVo();
		 
		 try {
			 conn = getConnection();
			 String query = "select pre_qnano, pre_title, pre_type, pre_regdate "
			 		+ "from ( "
			 		+ "select qnano, title, type, regdate, "
			 		+ "lag(qnano) over (order by qnano) pre_qnano, "
			 		+ "lag(title) over (order by qnano) pre_title, "
			 		+ "lag(type) over (order by qnano) pre_type, "
			 		+ "lag(regdate) over (order by qnano) pre_regdate "
			 		+ "from qnaboard "
			 		+ "where qnabck=1 "
			 		+ "order by qnano) "
			 		+ "where qnano = ?";
					 
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rs = pstmt.executeQuery();
			 
			if(rs.next()) {
				int pre_no = rs.getInt("pre_qnano");
				String pre_title = rs.getString("pre_title");
				String pre_type = rs.getString("pre_type");
				String pre_regDate = rs.getString("pre_regDate");
				vo = new QnaBoardVo(pre_no, pre_title, pre_type,  pre_regDate);
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
		 
		return vo;
	}

	@Override
	public QnaBoardVo nextQna(int qnaNo) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 QnaBoardVo vo = new QnaBoardVo();
		 
		 try {
			 conn = getConnection();
			 String query = "select next_qnano, next_title, next_type, next_regdate "
			 		+ "from ( "
			 		+ "select qnano, title, type, regdate, "
			 		+ "lead(qnano) over (order by qnano) next_qnano, "
			 		+ "lead(title) over (order by qnano) next_title, "
			 		+ "lead(type) over (order by qnano) next_type, "
			 		+ "lead(regdate) over (order by qnano) next_regdate "
			 		+ "from qnaboard "
			 		+ "where qnabck=1 "
			 		+ "order by qnano) "
			 		+ "where qnano = ? ";
					 
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rs = pstmt.executeQuery();
			 
			if(rs.next()) {
				int next_qnano = rs.getInt("next_qnano");
				String next_title = rs.getString("next_title");
				String next_type = rs.getString("next_type");
				String next_regDate = rs.getString("next_regDate");
				vo = new QnaBoardVo(next_qnano , next_title, next_type, next_regDate);
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
		 
		return vo;
	}


	@Override
	public QnaBoardVo prevNoAnsQna(int qnaNo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 QnaBoardVo vo = new QnaBoardVo();
		 
		 try {
			 conn = getConnection();
			 String query = "select pre_qnano, pre_title, pre_type, pre_regdate "
			 		+ "from ( "
			 		+ "select qnano, title, type, regdate, "
			 		+ "lag(qnano) over (order by qnano) pre_qnano, "
			 		+ "lag(title) over (order by qnano) pre_title, "
			 		+ "lag(type) over (order by qnano) pre_type, "
			 		+ "lag(regdate) over (order by qnano) pre_regdate "
			 		+ "from noansqna_view "
			 		+ "where qnabck=1 "
			 		+ "order by qnano) "
			 		+ "where qnano = ? ";
					 
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rs = pstmt.executeQuery();
			 
			if(rs.next()) {
				int pre_no = rs.getInt("pre_qnano");
				String pre_title = rs.getString("pre_title");
				String pre_type = rs.getString("pre_type");
				String pre_regDate = rs.getString("pre_regDate");
				vo = new QnaBoardVo(pre_no, pre_title, pre_type,  pre_regDate);
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
		 
		return vo;
	}


	@Override
	public QnaBoardVo nextNoAnsQna(int qnaNo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 QnaBoardVo vo = new QnaBoardVo();
		 
		 try {
			 conn = getConnection();
			 String query = "select next_qnano, next_title, next_type, next_regdate "
			 		+ "from ( "
			 		+ "select qnano, title, type, regdate, "
			 		+ "lead(qnano) over (order by qnano) next_qnano, "
			 		+ "lead(title) over (order by qnano) next_title, "
			 		+ "lead(type) over (order by qnano) next_type, "
			 		+ "lead(regdate) over (order by qnano) next_regdate "
			 		+ "from noansqna_view "
			 		+ "where qnabck=1 "
			 		+ "order by qnano) "
			 		+ "where qnano = ? ";
					 
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rs = pstmt.executeQuery();
			 
			if(rs.next()) {
				int next_qnano = rs.getInt("next_qnano");
				String next_title = rs.getString("next_title");
				String next_type = rs.getString("next_type");
				String next_regDate = rs.getString("next_regDate");
				vo = new QnaBoardVo(next_qnano , next_title, next_type, next_regDate);
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
		 
		return vo;
	}

	@Override
	public ArrayList<QnaBoardVo> getNoAnswerL() {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
		 
		 try {
			 conn = getConnection();
			 
			 String query = "select qna.* "
			 		+ "from( "
			 		+ "select rownum num, qn.* "
			 		+ "from (select q.*, m.memName "
			 		+ "from noansqna_view q, regmember m "
			 		+ "where q.memno = m.memno "
			 		+ "order by qnano desc) qn) qna "
			 		+ "where num between 1 and 3 ";

			 pstmt = conn.prepareStatement(query);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 QnaBoardVo vo = new QnaBoardVo();
				 
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setMemName(rs.getString("memname"));
				 vo.setNickname(rs.getString("nickname"));
				 vo.setTitle(rs.getString("title"));
				 vo.setType(rs.getString("type"));
				 vo.setRegDate(rs.getString("regdate"));
				 
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
	public ArrayList<QnaBoardVo> getMyQList(int memno, int page) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
		 
		 try {
			 conn = getConnection();
			 
			 String query = "select qn.* "
			 		+ "from( "
			 		+ "select rownum num, q.* "
			 		+ "from "
			 		+ "(select * "
			 		+ "from qnaboard q "
			 		+ "where q.qnabck = 1 "
			 		+ "and q.memno = ? "
			 		+ "order by qnano desc)q)qn "
			 		+ "where num between ? and ?";

			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, memno);
			 pstmt.setInt(2, 1+(page-1)*5 );
			 pstmt.setInt(3, page*5);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 QnaBoardVo vo = new QnaBoardVo();
				 
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setTitle(rs.getString("title"));
				 vo.setType(rs.getString("type"));
				 vo.setContent(rs.getString("content"));
				 vo.setRegDate(rs.getString("regdate"));
				 
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
	public int getMyQCount(int memno) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
				 
			String query = "select count(qnano) "
							+ "from qnaboard "
							+ "where qnabck=1 "
							+ "and memno=? ";
					 
			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, memno );			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 count = rs.getInt("count(qnano)");
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
		
		return count;
	}

	public int getNewOrderCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query = "select count(*) from orderinfo where ordercomplete=0 and ordercancel = 0";
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("getNewOrderCount error: " + ex);
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
}
