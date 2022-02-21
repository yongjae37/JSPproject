package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.javaex.vo.PointVo;

public class PointDaoImpl implements PointDao {

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
	public ArrayList<PointVo> getList(int memNo, int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PointVo> list = new ArrayList<PointVo>();

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select po.* " + " from( " + " select rownum num, p.* " + " from( " + " select * "
					+ " from point " + " where memno = ? " + " order by pno desc)p)po " + " where num between ? and ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, memNo);
			pstmt.setInt(2, 1 + (page - 1) * 5);
			pstmt.setInt(3, page * 5);

			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {

				System.out.println("포인트 작동");

				int pno = rs.getInt("pNo");
				int memno = rs.getInt("memNo");
				int point = rs.getInt("point");
				int history = rs.getInt("history");
				String pdate = rs.getString("pDate");
				String pdesc = rs.getString("pDesc");

				PointVo vo = new PointVo(pno, memno, point, history, pdesc, pdate);
				System.out.println(vo.toString());
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
	public PointVo getPoint(int memNo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PointVo pvo = new PointVo();

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select po.point " + "from( " + "select rownum num, p.* " + "from( " + "select * "
					+ "from point " + "where memno = ? " + "order by pno desc)p)po " + "where num = 1 ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			// 4.결과처리
			if (rs.next()) {

				pvo.setPoint(rs.getInt("point"));
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

		return pvo;
	}

	@Override
	public int getPointCount(int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PointVo> list = new ArrayList<PointVo>();
		int count = 0;

		try {
			conn = getConnection();
			String query = "select count(pno) " + "from point " + "where memno = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count(pno)");
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);

			}
		}

		return count;
	}

	public int insertProcess(int memNo, int payment , String pDesc) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		int balance = check(memNo);
		int result = balance - payment;
		try {
			conn = getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into point values (seq_point_no.nextval, ? , ? , ?, to_char(sysdate, 'YY-MM-DD HH24:MI'), ? )";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, result);
			pstmt.setInt(3, -payment);
			pstmt.setString(4, pDesc);
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 포인트 등록");
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
		return result;
	}

	@Override
	public int check(int memNo) {  //현재 잔여 포인트 확인
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select point from point where memno = " + memNo + " and rowNum = 1 ORDER by pNo DESC ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			if (rs.next()) {
				int proNo = rs.getInt("point");

				point = proNo;
				
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

		return point;
	}

	@Override
	public int insert(int memNo) {	//신규 가입 시 포인트 2000 적립
		Connection conn = null;
		PreparedStatement pstmt = null;

		int count = 0;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into point values (seq_point_no.nextval, ? , 2000 , 2000 , to_char(sysdate, 'YY-MM-DD HH24:MI') , '신규회원 가입' )";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 포인트 등록");

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

	public int pointAfterReview(int memNo) {	//리뷰 작성 후 포인트 500 지급
		Connection conn = null;
		PreparedStatement pstmt = null;

		int count = 0;
		int prevPoint = check(memNo);
		
		try {
			conn = getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into point values(seq_point_no.nextval, ?, ?, 500, to_char(sysdate, 'YY-MM-DD HH24:MI'), '리뷰작성')";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, prevPoint+500);
			
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 포인트 등록");

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
	
	public int pointAfterDelete(int memNo) { //리뷰 삭제 후 포인트 500 차감
		Connection conn = null;
		PreparedStatement pstmt = null;

		int count = 0;
		int prevPoint = check(memNo);
		
		try {
			conn = getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into point values(seq_point_no.nextval, ?, ?, -500, to_char(sysdate, 'YY-MM-DD HH24:MI'), '리뷰삭제')";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, prevPoint-500);
			
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 포인트 차감");

		} catch (SQLException e) {
			System.out.println("insert(delete) error:" + e);
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
				System.out.println("insert(delete) error:" + e);
			}

		}

		return count;
	}
	
	public int afterOrder(int orderNo) { //구매 시 적립되는 포인트 값(주문가격의 1%) 구하기 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int pointToAdd = 0;
		float totalprice = 0;
		
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " select totalprice "
					+ " from orderinfo "
					+ " where orderno = "+orderNo;
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalprice = rs.getInt("totalprice");
				
			}
			pointToAdd = (int) Math.floor(totalprice * 0.01);
			// 4.결과처리
			System.out.println(pointToAdd);

		} catch (SQLException e) {
			System.out.println("afterOrder error:" + e);
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
				System.out.println("afterOrdert error:" + e);
			}

		}

		return pointToAdd;
	}
	
	
	@Override
	public int orderPoint(int orderNo, int memNo) { //구매 후 (배송 완료 후) 포인트 적립 
		//afterorder() == pointvalue
		Connection conn = null;
		PreparedStatement pstmt = null;
		int pointValue = afterOrder(orderNo);  //구매 적립금
		int prevPoint = check(memNo);  //최신 누적 포인트
		int updatedValue = pointValue + prevPoint;
		int count = 0;
		System.out.println("pv: "+ pointValue + "pp: "+ prevPoint + "uv: "+ updatedValue);
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into point values (seq_point_no.nextval, ? , ? , ? , to_char(sysdate, 'YY-MM-DD HH24:MI') , '구매 포인트 적립' )";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, updatedValue);
			pstmt.setInt(3, pointValue);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 포인트적립 등록");

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
	
	
}
