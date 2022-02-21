package com.javaex.vo;

public class PointVo {
	private int pNo;
	private int memNo;
	private int point;
	private int history;
	private String pDate;
	private String pDesc;

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int gethistory() {
		return history;
	}

	public void sethistory(int history) {
		this.history = history;
	}

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

	public String getpDesc() {
		return pDesc;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	public PointVo(int pNo, int memNo, int point, int history, String pDesc, String pDate) {
		super();
		this.pNo = pNo;
		this.memNo = memNo;
		this.point = point;
		this.history = history;
		this.pDate = pDate;
		this.pDesc = pDesc;
	}

	public PointVo(int point) {
		super();
		this.point = point;
	}

	public PointVo() {

	}

	@Override
	public String toString() {
		return "PointVo [pNo=" + pNo + ", memNo=" + memNo + ", point=" + point + ", history=" + history + ", pDate="
				+ pDate + ", pDesc=" + pDesc + "]";
	}

}
