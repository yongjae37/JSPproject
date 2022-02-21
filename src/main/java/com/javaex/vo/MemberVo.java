package com.javaex.vo;

public class MemberVo {
	private int memNo;
	private String memName;
	private String memPhone;
	private String memPostc;
	private String memDoro;
	private String memJibun;
	private String memAdd;
	private String memId;
	private String memPass;
	private String adminCk;
	private String memCk;

	public MemberVo() {
		super();
	}

	public MemberVo(String memId, String memPass) {
		// TODO Auto-generated constructor stub
		this.memId = memId;
		this.memPass = memPass;
	}

	public MemberVo(String memName, String memPhone, String memPostc, String memDoro, String memJibun, String memAdd,
			String memId, String memPass) {
		super();
		this.memName = memName;
		this.memPhone = memPhone;
		this.memPostc = memPostc;
		this.memDoro = memDoro;
		this.memJibun = memJibun;
		this.memAdd = memAdd;
		this.memId = memId;
		this.memPass = memPass;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
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
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemAdd() {
		return memAdd;
	}
	public void setMemAdd(String memAdd) {
		this.memAdd = memAdd;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	
	public String getAdminCk() {
		return adminCk;
	}
	public void setAdminCk(String adminCk) {
		this.adminCk = adminCk;
	}

	public String getMemCk() {
		return memCk;
	}

	public void setMemCk(String memCk) {
		this.memCk = memCk;
	}
	public String getMemPostc() {
		return memPostc;
	}

	public void setMemPostc(String memPostc) {
		this.memPostc = memPostc;
	}

	public String getMemDoro() {
		return memDoro;
	}

	public void setMemDoro(String memDoro) {
		this.memDoro = memDoro;
	}

	public String getMemJibun() {
		return memJibun;
	}

	public void setMemJibun(String memJibun) {
		this.memJibun = memJibun;
	}
}
