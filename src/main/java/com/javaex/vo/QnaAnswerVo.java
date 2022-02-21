package com.javaex.vo;

public class QnaAnswerVo {
	private int ansNo;
	private int qnaNo;
	private int memNo;
	private String answer;
	private String regDate;
	private int answerCk;
	
	
	public QnaAnswerVo() {
		super();
	}	
	public QnaAnswerVo(int qnaNo, int memNo, String answer) {
		super();
		this.qnaNo = qnaNo;
		this.memNo = memNo;
		this.answer = answer;
	}

	public int getAnsNo() {
		return ansNo;
	}
	public void setAnsNo(int ansNo) {
		this.ansNo = ansNo;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getAnswerCk() {
		return answerCk;
	}
	public void setAnswerCk(int answerCk) {
		this.answerCk = answerCk;
	}
	@Override
	public String toString() {
		return "QnaAnswerVo [ansNo=" + ansNo + ", qnaNo=" + qnaNo + ", memNo=" + memNo + ", answer=" + answer
				+ ", regDate=" + regDate + ", answerCk=" + answerCk + "]";
	}

}
