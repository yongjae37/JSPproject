package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javaex.vo.MemberVo;
import com.javaex.vo.OrderInfoVo;

public class MemberDaoImpl implements MemberDao {
	
	private Connection getConnection() throws SQLException { 
	    Connection conn = null;
	    try {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String dburl = "jdbc:oracle:thin:@3.38.190.21:1521:xe";
	      conn = DriverManager.getConnection(dburl, "mysiteb", "1234");
	    } catch (ClassNotFoundException e) {
	      System.err.println("JDBC 드라이버 로드 실패!"); 
	    }
	    return conn;
	  }


	@Override
	public int insert(MemberVo vo) {

		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "insert into regMember values (seq_regmember_no.nextval, ?, ?, ? , ?, ?, ? , ?, ?, 0 ,1 )";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setString(1, vo.getMemName());
			 pstmt.setString(2, vo.getMemPhone());
			 pstmt.setString(3, vo.getMemPostc());
			 pstmt.setString(4, vo.getMemDoro());
			 pstmt.setString(5, vo.getMemJibun());
			 pstmt.setString(6, vo.getMemAdd());
			 pstmt.setString(7, vo.getMemId());
			 pstmt.setString(8, vo.getMemPass());
			 
			 System.out.println("name->"+vo.getMemName());
				System.out.println("phone->"+ vo.getMemPhone());
				System.out.println("postc->"+vo.getMemPostc());
				System.out.println("doro->"+vo.getMemDoro());
				System.out.println("jibun->"+vo.getMemJibun());
				System.out.println("address->"+vo.getMemAdd());
				System.out.println("id->"+vo.getMemId());
				System.out.println("password->"+vo.getMemPass());
			 
			 count = pstmt.executeUpdate();
			 
			 System.out.println(count + "명 회원가입 완료");
			 
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
	public MemberVo getMember(String memId, String memPass) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 MemberVo vo = null;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "select * from regmember where memId= ? and memck = 1 and mempass = ? ";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setString(1, memId );
			 pstmt.setString(2, memPass);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 
				 vo = new MemberVo();
				 int no = rs.getInt("memno");
				String memname = rs.getString("memname");
				String memphone = rs.getString("memphone");
				String mempostc = rs.getString("mempostc");
				String memdoro = rs.getString("memdoro");
				String memjibun = rs.getString("memjibun");
				String memadd = rs.getString("memadd");
				String adminck = rs.getString("adminck");
				String memck = rs.getString("memck");
				
				vo.setMemId(memId);
				vo.setMemPass(memPass);
				vo.setMemNo(no);
				vo.setMemName(memname);
				vo.setMemPhone(memphone);
				vo.setMemPostc(mempostc);
				vo.setMemDoro(memdoro);
				vo.setMemJibun(memjibun);
				vo.setMemAdd(memadd);
				vo.setMemPhone(memphone);
				
				
				
				
				vo.setAdminCk(adminck);
				vo.setMemCk(memck);
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
	public MemberVo getMember(int memNo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 MemberVo vo = null;
		 
		 try {
			 conn = getConnection();
			
			 String query ="select * from regmember where memno = ? and memck = 1";
					 
			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1,  memNo);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 memNo = rs.getInt("memno");
				 String memName = rs.getString("memname");
				 String memPhone = rs.getString("memphone");
				 String mempostc = rs.getString("mempostc");
			     String memdoro = rs.getString("memdoro");
			     String memjibun = rs.getString("memjibun");
				 String memadd = rs.getString("memadd");
				 String memId =rs.getString("memid");
				 String memPass = rs.getString("mempass");
				 String memCk = rs.getString("memck");
				 
				 vo = new MemberVo();
				 vo.setMemNo(memNo);
				 vo.setMemName(memName);
				 vo.setMemPhone(memPhone);
				 vo.setMemPostc(mempostc);
				 vo.setMemDoro(memdoro);
				 vo.setMemJibun(memjibun);
				 vo.setMemAdd(memadd);
				 vo.setMemId(memId);
				 vo.setMemPass(memPass);
				 vo.setMemCk(memCk);			 
				 
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
			 } return vo;
	}

	@Override
	public int update(MemberVo vo) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count =0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "update regmember set memname = ?, memphone = ?, mempostc = ?, memdoro = ?, memjibun=?, memadd = ?, mempass= ? where memno= ? ";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setString(1, vo.getMemName());
			 pstmt.setString(2, vo.getMemPhone());
			 pstmt.setString(3, vo.getMemPostc());
			 pstmt.setString(4, vo.getMemDoro());
			 pstmt.setString(5, vo.getMemJibun());
			 pstmt.setString(6, vo.getMemAdd());
			 pstmt.setString(7, vo.getMemPass());
			 pstmt.setInt(8, vo.getMemNo());
			 
			 count = pstmt.executeUpdate();
			 
			System.out.println(count + "건 수정완료");
		 } catch (SQLException e) {
			 System.out.println("error:" + e);
		 }finally {
			 try {
				 if(pstmt != null) pstmt.close();
				 if(conn != null) conn.close();
			 } catch (SQLException e) {
				 System.out.println("error:" + e);
				 
			 }
		 } return count;
	}


	@Override
	public int delete(MemberVo vo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "update regmember set memck = 0 where memid = ? and mempass= ? ";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setString(1, vo.getMemId());
			 pstmt.setString(2, vo.getMemPass());
			 
			 count = pstmt.executeUpdate();
			 
			 System.out.println(count + "명 탈퇴");
			 
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
	public ArrayList<OrderInfoVo> getList(String no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		
		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from orderInfo where memid = ? and memck = 1";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, no);
			
			
			System.out.println(query);
			System.out.println("no : " + no);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				
				System.out.println("작동");
				String date = rs.getString("orderDate");
				int price = rs.getInt("totalPrice");
				
				OrderInfoVo vo = new OrderInfoVo(date, price);
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
	public ArrayList<OrderInfoVo> getList(int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		
		try {
		  conn = getConnection();
		  
		  
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from orderInfo where memNo = ? and memck = 1";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,memNo );
			
			System.out.println("no : " + memNo);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				
				System.out.println("작동");
				String date = rs.getString("orderDate");
				int price = rs.getInt("totalPrice");
				
				OrderInfoVo vo = new OrderInfoVo(date, price);
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

	public String idCheck(String memId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		int cnt = 0;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String sql = " select count(*) cnt " + "   from regmember " + "  where memid = ? and memck = 1";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();

			// 4.결과처리
			rs.next();
			cnt = rs.getInt("cnt"); // 1 0
			System.out.println("cnt:" + cnt);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		if (cnt == 1) {
			return "true";
		} else {
			return "false";
		}
	}

	
	public int chanegepw(String pw, String name, String id) {
        // TODO Auto-generated method stub
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count =0;
        
        try {
            conn = getConnection();
            String query = "update regMember set mempass= ? where memname= ? and memid = ? and memck = 1";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, pw);
            pstmt.setString(2, name);
            pstmt.setString(3, id);
        
            System.out.println("name : " + name);
            System.out.println("id : " + id);
            System.out.println("pw : " + pw);
            
            count = pstmt.executeUpdate();
            System.out.println("비밀번호 수정완료");
            
            
            
        } catch (SQLException e) {
            System.out.println("error: "+ e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }
        }
        return count;
    }

	@Override
    public String FindMember(String name, String phone) {
        // TODO Auto-generated method stub
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String vo = null;
        
        try {
            conn = getConnection();
            String query = "select * from regMember where memname = ? and memphone = ? and memck = 1";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
        
            System.out.println("phone : " + phone);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                
                 
                 int no = rs.getInt("memno");
                String memname = rs.getString("memname");
                String memphone = rs.getString("memphone");
                String mempostc = rs.getString("mempostc");
                String memdoro = rs.getString("memdoro");
                String memjibun = rs.getString("memjibun");
                String memadd = rs.getString("memadd");
                String adminck = rs.getString("adminck");
                String memck = rs.getString("memck");
                vo = rs.getString("memid");
                
                
                
            }
            
        } catch (SQLException e) {
            System.out.println("error: "+ e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }
        }
        return vo;
    }

}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       