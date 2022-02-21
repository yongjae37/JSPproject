package com.javaex.vo;

public class ReviewVo {
	private int revNo;
	private int memNo;
	private int proNo;
	private String title;
	private String content;
	private String revDate;
	private int rate;
	private int revHit;
	private String file1;
	private String file2;
	private String memId;
	private String proName;
	private int uavail;
	
	public ReviewVo() {
	}
		
	
	public ReviewVo(int memNo, int proNo, String title, String content, int rate, String file1, String file2) {
		this.memNo = memNo;
		this.proNo = proNo;
		this.title = title;
		this.content = content;
		this.rate = rate;
		this.file1 = file1;
		this.file2 = file2;
	}

	public ReviewVo(int revNo, int memNo, int proNo, String title, String content, int rate, String file1,
			String file2) {
		this.revNo = revNo;
		this.memNo = memNo;
		this.proNo = proNo;
		this.title = title;
		this.content = content;
		this.rate = rate;
		this.file1 = file1;
		this.file2 = file2;
	}


	public int getRevNo() {
		return revNo;
	}
	public void setRevNo(int revNo) {
		this.revNo = revNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public int getProNo() {
		return proNo;
	}
	public void setProNo(int proNo) {
		this.proNo = proNo;
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
	public String getRevDate() {
		return revDate;
	}
	public void setRevDate(String revDate) {
		this.revDate = revDate;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public int getRevHit() {
		return revHit;
	}

	public void setRevHit(int revHit) {
		this.revHit = revHit;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
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

	public int getUavail() {
		return uavail;
	}


	public void setUavail(int uavail) {
		this.uavail = uavail;
	}

	
}
