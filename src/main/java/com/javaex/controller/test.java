package com.javaex.controller;
import java.util.Properties;

import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

public class test {
	

	public static void main(String[] args) {
	}
	
	public static void naverMailSend(String ck, String mi) { 
		
		String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정 
		String user = "dlaalgus8956@naver.com"; // 패스워드 
		String password = "!seung0116*";  // SMTP 서버 정보를 설정한다. 
		
//    <prop key="mail.smtp.starttls.enable">true</prop>
//    <prop key="mail.smtp.auth">true  </prop>
//    <prop key="mail.transport.protocol">smtp</prop>
//    <prop key="mail.debug">true</prop>
//    <prop key="mail.smtp.ssl.trust">smtp.gmail.com</prop>
//    <prop key="mail.smtp.ssl.protocols">TLSv1.2</prop>
		
		
		Properties props = new Properties(); 
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", 587); 
		props.put("mail.smtp.auth", "true"); 
		
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug", "true");
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		
        System.out.println(ck);
        System.out.println("받을 주소는 " + mi);
		
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication(user, password); 
			} 
		});
		
		//session.getProperties().put("mail.smtp.ssl.trust", "smtp.naver.com");
		
		try { 
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(user)); 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mi));
			
			// 메일 제목 
			message.setSubject("비밀번호 찾기 인증 번호 발송");
			// 메일 내용 
			message.setText("인증 번호는 " + ck + "입니다."); 
			
			// send the message 
			Transport.send(message); 
			System.out.println("Success Message Send"); 
			
		} catch (MessagingException e) { 
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
		
	}
	
public static void naverMailSend2(String ck, String mi) { 
		
		String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정 
		String user = "dlaalgus8956@naver.com"; // 패스워드 
		String password = "!seung0116*";  // SMTP 서버 정보를 설정한다. 
		
//    <prop key="mail.smtp.starttls.enable">true</prop>
//    <prop key="mail.smtp.auth">true  </prop>
//    <prop key="mail.transport.protocol">smtp</prop>
//    <prop key="mail.debug">true</prop>
//    <prop key="mail.smtp.ssl.trust">smtp.gmail.com</prop>
//    <prop key="mail.smtp.ssl.protocols">TLSv1.2</prop>
		
		
		Properties props = new Properties(); 
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", 587); 
		props.put("mail.smtp.auth", "true"); 
		
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug", "true");
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		
        System.out.println(ck);
        System.out.println("받을 주소는 " + mi);
		
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication(user, password); 
			} 
		});
		
		//session.getProperties().put("mail.smtp.ssl.trust", "smtp.naver.com");
		
		try { 
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(user)); 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mi));
			
			// 메일 제목 
			message.setSubject("회원가입 이메일 인증 번호 발송");
			// 메일 내용 
			message.setText("인증 번호는 " + ck + "입니다."); 
			
			// send the message 
			Transport.send(message); 
			System.out.println("Success Message Send"); 
			
		} catch (MessagingException e) { 
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
		
	}
	
}