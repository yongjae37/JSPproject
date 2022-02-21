package com.javaex.vo;

public class BBoardVo {
	private int bNo;
	private int memNo;
	private String title;
	private String content;
	private String regDate;
	
	
	public BBoardVo() {
	}

	public BBoardVo(int bNo, String title, String content, String regDate) {
		this.bNo = bNo;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		
	}
	
	public BBoardVo(String title, String content, String regDate) {
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		
	}
	
	public BBoardVo(String title, String content) {
		this.title = title;
		this.content = content;
		
	}
	
	public BBoardVo(int bNo, String title, String regDate) {
		this.bNo = bNo;
		this.title = title;
		this.regDate = regDate;
	}
	
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "BBoardVo [bNo=" + bNo + ", title=" + title + ", content=" + content + ", regDate="
				+ regDate + "]";
	}
	
	
	
	
}
