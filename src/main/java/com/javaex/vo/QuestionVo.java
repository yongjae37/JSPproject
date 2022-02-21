package com.javaex.vo;

import java.util.List;

public class QuestionVo {
	private int qno;
	private int prono;
	private int memno;
	private String qdate;
	private String qcontent;
	private int qAvail;
	private String memId;
	private AnswerVo avo;
	private String proName;
	private List<AnswerVo> alist;
	
	public QuestionVo() {
	}
	
	public QuestionVo(int prono, int memno, String qcontent, int qAvail) {
		this.prono = prono;
		this.memno = memno;
		this.qcontent = qcontent;
		this.qAvail = qAvail;
	}
	
	
	public QuestionVo(int prono, int memno, String qcontent) {
		this.prono = prono;
		this.memno = memno;
		this.qcontent = qcontent;
	}

	public int getQno() {
		return qno;
	}
	public AnswerVo getAvo() {
		return avo;
	}
	public void setAvo(AnswerVo avo) {
		this.avo = avo;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public int getProno() {
		return prono;
	}
	public void setProno(int prono) {
		this.prono = prono;
	}
	public int getMemno() {
		return memno;
	}
	public void setMemno(int memno) {
		this.memno = memno;
	}
	public String getQdate() {
		return qdate;
	}
	public void setQdate(String qdate) {
		this.qdate = qdate;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public int getqAvail() {
		return qAvail;
	}
	public void setqAvail(int qAvail) {
		this.qAvail = qAvail;
	}
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public List<AnswerVo> getAlist() {
		return alist;
	}

	public void setAlist(List<AnswerVo> alist) {
		this.alist = alist;
	}

	@Override
	public String toString() {
		return "QuestionVo [qno=" + qno + ", prono=" + prono + ", memno=" + memno + ", qdate=" + qdate + ", qcontent="
				+ qcontent + ", qAvail=" + qAvail + ", memId=" + memId + ", avo=" + avo + ", proName=" + proName
				+ ", alist=" + alist + "]";
	}

	
}
