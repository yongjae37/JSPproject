package com.javaex.vo;

public class AnswerVo {
	private int aNo;
	private int qNo;
	private int memNo;
	private String aDate;
	private String aContent;
	private int aAvail;

	public AnswerVo() {
	}

	public AnswerVo(String aDate, String aContent) {
		this.aDate = aDate;
		this.aContent = aContent;
	}

	public AnswerVo(int qNo, int memNo, String aContent) {
		this.qNo = qNo;
		this.memNo = memNo;
		this.aContent = aContent;
	}

	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
	}
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getaDate() {
		return aDate;
	}
	public void setaDate(String aDate) {
		this.aDate = aDate;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}
	public int getaAvail() {
		return aAvail;
	}
	public void setaAvail(int aAvail) {
		this.aAvail = aAvail;
	}

	@Override
	public String toString() {
		return "AnswerVo [aNo=" + aNo + ", qNo=" + qNo + ", memNo=" + memNo + ", aDate=" + aDate + ", aContent="
				+ aContent + ", aAvail=" + aAvail + "]";
	}

	
	
	
}
