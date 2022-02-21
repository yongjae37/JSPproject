package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.AnswerVo;
import com.javaex.vo.ProductVo;
import com.javaex.vo.QuestionVo;
import com.javaex.vo.ReviewVo;

public class QuestionDaoImpl implements QuestionDao{
	
	private static QuestionDao instance;
	public QuestionDaoImpl()	{
		
	}
	public static QuestionDao getInstance() {
		if(instance == null) {
			instance = new QuestionDaoImpl();
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
	public int insert(QuestionVo vo) {	//질문 입력
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;

		try {
		  conn = getConnection();
		  
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into question\r\n"
					+ "values (seq_question_no.nextval, ?, ?, to_char(sysdate, 'YY-MM-DD HH24:MI'), ?, 1)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getProno());
			pstmt.setInt(2, vo.getMemno());
			pstmt.setString(3, vo.getQcontent());

						
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
	public int delete(int qNo) {	//db에 해당 질문의 qAvail 값을 0으로 수정
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update question\r\n"
					+ "set qavail = 0\r\n"
					+ "where qno = " + qNo;
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
	public int getListCount(int proNo) {	//질문 갯수 세기 (for pagination)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query;
		
		
		if(proNo == 0) {
			query = "select count(*) from question where qavail = 1";
		}else {
			query = "select count(*) from question where qavail = 1 and proNo = "+proNo;
		}
		
		try {
			
		
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("ListCountError: " + ex);
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
	public List<QuestionVo> getList(int page, int limit, int proNo) {	//질문 리스트 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = getListCount(proNo);
		AnswerDao dao = new AnswerDaoImpl();
		int start = (page - 1) * limit;
		int index = start + 1;
		String sql;
		
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		
		if (proNo == 0) {
			sql = "select q.*, m.memid\r\n"
					+ "from question q, product p, regmember m\r\n"
					+ "where q.prono = p.prono\r\n"
					+ "and q.qavail = 1\r\n"
					+ "and q.memno = m.memno\r\n"
					+ "order by qdate desc";
			System.out.println("전체 다 뿌림");
		}
		else { 
			sql = "select q.*, m.memid\r\n"
					+ "from question q, product p, regmember m\r\n"
					+ "where q.prono = p.prono\r\n"
					+ "and q.prono = "+proNo+"\r\n"
					+ "and q.qavail = 1\r\n"
					+ "and q.memno = m.memno\r\n"
					+ "order by qdate desc";
			System.out.println("특정 상품 리스트 뿌림");
		}
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				QuestionVo vo = new QuestionVo();
				int qNo = rs.getInt("qNo");
				vo.setProno(rs.getInt("proNo"));
				vo.setMemno(rs.getInt("memNo"));
				vo.setQno(qNo);
				vo.setQcontent(rs.getString("qContent"));
				vo.setQdate(rs.getString("qDate"));
				vo.setqAvail(rs.getInt("qAvail"));
				vo.setMemId(rs.getString("memid").substring(0,2) + "***"); 
				vo.setAvo(dao.getAnswer(vo.getQno()));
				vo.setAlist(getAnswerlist(qNo));
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

	public QuestionVo getQuestion(int qnum) {	//특정 질문 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QuestionVo vo = new QuestionVo();
		
		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select q.*, r.memid, p.proname from question q, regmember r, product p where r.memno = q.memno and q.prono = p.prono and qNo = "+qnum;
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			if (rs.next()) {
				
				int qno = rs.getInt("qno");
				int prono = rs.getInt("prono");
				int memno = rs.getInt("memno");
				String qdate = rs.getString("qdate");
				String qcontent = rs.getString("qcontent");
				int qAvail = rs.getInt("qAvail");
				String memId = rs.getString("memId");
				String proName = rs.getString("proname");
				
				vo = new QuestionVo();
				vo.setQno(qno);
				vo.setProno(prono);
				vo.setMemno(memno);
				vo.setQdate(qdate);
				vo.setQcontent(qcontent);
				vo.setqAvail(qAvail);
				vo.setMemId(memId);
				vo.setProName(proName);
				
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

		return vo;
	}

	public List<AnswerVo> getAnswerlist(int qNo) {	//답변 리스트 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select a.*\r\n"
				+ "from question q, answer a\r\n"
				+ "where q.qno = a.qno\r\n"
				+ "and a.aavail = 1\r\n"
				+ "and q.qno = " + qNo;
		
		List<AnswerVo> list = new ArrayList<AnswerVo>();
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AnswerVo vo = new AnswerVo();

				vo.setaNo(rs.getInt("aNo"));
				vo.setqNo(rs.getInt("qNo"));
				vo.setaDate(rs.getString("aDate"));
				vo.setaContent(rs.getString("aContent"));
				
				list.add(vo);
				
			}
			
		} catch (Exception ex) {
			System.out.println("getAnswerlist() error : " + ex);
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
	public List<QuestionVo> getMypqList(int memno, int page) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QuestionVo> list = new ArrayList<QuestionVo>();
		 
		 try {
			 conn = getConnection();
			 
			 String query = "select prq.* "
			 		+ "from "
			 		+ "(select rownum num, pq.* "
			 		+ "from (select q.qno, q.prono, q.memno, q.qdate, q.qavail, p.proname "
			 		+ "from question q, product p  "
			 		+ "where q.qavail = 1 "
			 		+ "and p.prono = q.prono  "
			 		+ "and q.memno = ? "
			 		+ "order by qno desc)pq)prq "
			 		+ "where num between ? and ?";

			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, memno);
			 pstmt.setInt(2, 1+(page-1)*5 );
			 pstmt.setInt(3, page*5);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 QuestionVo vo = new QuestionVo();
				 
				 vo.setQno(rs.getInt("qno"));
				 vo.setProno(rs.getInt("prono"));
				 //vo.setMemno(rs.getInt("memno"));
				 vo.setProName(rs.getString("proname"));
				 vo.setQdate(rs.getString("qdate"));
				 //vo.setQcontent(rs.getString("qcontent"));
				 
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
	public int getMypqCount(int memno) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QuestionVo> list = new ArrayList<QuestionVo>();
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "select count(qno) "
			 		+ "from question "
			 		+ "where qavail=1 "
			 		+ "and memno=? ";

			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, memno);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 count = rs.getInt("count(qno)");
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
		
		return count ;
	}

	public int allQlistCount() {	//모든 질문 갯수 (for manager)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query = "select count(*) from question where qavail = 1";
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("allQlistCount error: " + ex);
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
	
	public List<QuestionVo> allQlist (int page, int limit) {	//모든 질문 리스트 (for manager)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = allQlistCount();
		AnswerDao dao = new AnswerDaoImpl();
		int start = (page - 1) * limit;
		int index = start + 1;
		String sql;
		
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		
		sql = "select q.*, m.memid, p.proname\r\n"
				+ "from question q, product p, regmember m\r\n"
				+ "where q.prono = p.prono\r\n"
				+ "and q.qavail = 1\r\n"
				+ "and q.memno = m.memno\r\n"
				+ "order by qdate desc";
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				QuestionVo vo = new QuestionVo();
				int qNo = rs.getInt("qno");
				vo.setProno(rs.getInt("prono"));
				vo.setMemno(rs.getInt("memno"));
				vo.setQno(qNo);
				vo.setProName(rs.getString("proName"));
				vo.setQcontent(rs.getString("qcontent"));
				vo.setQdate(rs.getString("qdate"));
				vo.setqAvail(rs.getInt("qAvail"));
				vo.setMemId(rs.getString("memId")); 
				vo.setAvo(dao.getAnswer(vo.getQno()));
				vo.setAlist(getAnswerlist(qNo));
				list.add(vo);
				if (index < (start + limit) && index <= total_record)
					index++;
			
				else 
					break;
			}
			System.out.println("리스트 프린트 결과:" +list);
		} catch (Exception ex) {
			System.out.println("allQlist error : " + ex);
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
	public int getNoAnsCount() {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 String query = "select count(*) from(  "
			 		+ "select count(qa.qno) counta  "
			 		+ "from (select q.* from question q where q.qavail = 1 ) qb  "
			 		+ "left join answer qa  "
			 		+ "on qa.qno = qb.qno  "
			 		+ "group by qb.qno)  "
			 		+ "where counta = 0";
					 
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
	
}
