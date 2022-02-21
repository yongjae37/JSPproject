<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Random"%>
<%@ page import="javax.servlet.http.HttpServletResponse" %>

<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.MessagingException"%>
<%@page import="javax.mail.PasswordAuthentication"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="java.util.Properties"%>
<%@ page import="com.javaex.controller.test"%>




<%
      // 요청파라미터로  중복할 체크할 아이디
      String memName = request.getParameter("name");  // id 중복체크
      String memId = request.getParameter("memId");
      String number = request.getParameter("number"); 
      System.out.println("이름은: " + memName);
      
      Enumeration e = request.getParameterNames();
      while ( e.hasMoreElements() ){
        String name = (String) e.nextElement();
        String[] values = request.getParameterValues(name);   
        for (String value : values) {
          System.out.println("name=" + name + ",value=" + value);
        }   
      }
      
      
      int cnt = 0;
      //   cnt=1  아이디 사용중이다.  
      //       0  아이디 사용가능하다.
      String sql = " select count(*) cnt " + 
                   "   from regmember " + 
                   "  where memname = ? and memid = ?";
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      String url = "jdbc:oracle:thin:@3.38.190.21:1521:XE";
      String user = "mysiteB";
      String pass = "1234";
      
      
      try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(url, user, pass);
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, memName);
        pstmt.setString(2, memId);
        rs = pstmt.executeQuery();
        if(rs.next()){
          cnt = rs.getInt("cnt");  // 1      0  
        System.out.println(cnt);
          
          
          
        }
        
      }catch(Exception ex){
         ex.printStackTrace();
      }finally{
        try{
          if(rs != null)   rs.close();
          if(pstmt != null) pstmt.close();
          if(conn != null) conn.close();
        }catch(Exception ex){
          ex.printStackTrace();
        }
      }
      
      if(cnt == 1){
    	  
    	  
    	  test t = new test();
    	  test.naverMailSend(number, memId);

    	  
    	  
        out.print("true");
      }else{
        out.print("false");
      }
      
      
      
      
      
      
      
      
      
      
%>