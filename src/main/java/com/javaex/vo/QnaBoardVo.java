package com.javaex.vo;

public class QnaBoardVo {
	private int qnaNo;
	private int memNo;
	private String memName;
	private String nickname;
	private String pass;
	private String title;
	private String type;
	private String content;
	private String regDate;
	private int priv;
	private int qnabCk;
	private int ansCnt;

	
	public int getAnsCnt() {
		return ansCnt;
	}
	public void setAnsCnt(int ansCnt) {
		this.ansCnt = ansCnt;	
	}
	public int getQnabCk() {
		return qnabCk;
	}
	public void setQnabCk(int qnabCk) {
		this.qnabCk = qnabCk;
	}
	public int getPriv() {
		return priv;
	}
	public void setPriv(int priv) {
		this.priv = priv;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	
	public QnaBoardVo() {
		super();
	}
	
//	public QnaBoardVo(String title, String content, int memNo) {
//		super();
//		this.memNo = memNo;
//		this.title = title;
//		this.content = content;
//	}

	public QnaBoardVo(int memNo, String title, String content) {
		super();
		this.memNo = memNo;
		this.title = title;
		this.content = content;
	}

	public QnaBoardVo(String nickname, String pass, String title, String type, String content, int priv) {
	super();
	this.nickname = nickname;
	this.pass = pass;
	this.title = title;
	this.type = type;
	this.content = content;
	this.priv = priv;
}
	public QnaBoardVo( String title, String type, String content, String pass, int qnaNo, int priv) {
	super();
	this.qnaNo = qnaNo;
	this.title = title;
	this.type = type;
	this.pass = pass;
	this.content = content;
	this.priv = priv;
}
	public QnaBoardVo(int memNo, String title, String type, String content, int priv) {
		super();
		this.memNo = memNo;
		this.title = title;
		this.type = type;
		this.content = content;
		this.priv = priv;
	}
	
	
	public QnaBoardVo(int qnaNo, String title, String type, String regDate) {
		super();
		this.qnaNo = qnaNo;
		this.title = title;
		this.type = type;
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "QnaBoardVo [qnaNo=" + qnaNo + ", memNo=" + memNo + ", memName=" + memName + ", nickname=" + nickname
				+ ", pass=" + pass + ", title=" + title + ", type=" + type + ", content=" + content + ", regDate="
				+ regDate + ", priv=" + priv + ", qnabCk=" + qnabCk + "]";
	}
	
	
	
	
	
	
	
}

