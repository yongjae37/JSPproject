package com.javaex.vo;

public class ShippingVo {
	private int orderNo;
	private String sName;
	private String sPostc;
	private String sDoro;
	private String sJibun;
	private String sAdd;
	private String sPhone;
	public ShippingVo() {
		super();
	}
	public ShippingVo(int orderNo, String sName, String sPostc, String sDoro, String sJibun, String sAdd, String sPhone,
			String sSps) {
		super();
		this.orderNo = orderNo;
		this.sName = sName;
		this.sPostc = sPostc;
		this.sDoro = sDoro;
		this.sJibun = sJibun;
		this.sAdd = sAdd;
		this.sPhone = sPhone;
		this.sSps = sSps;
	}
	private String sSps;
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsPostc() {
		return sPostc;
	}
	public void setsPostc(String sPostc) {
		this.sPostc = sPostc;
	}
	public String getsDoro() {
		return sDoro;
	}
	public void setsDoro(String sDoro) {
		this.sDoro = sDoro;
	}
	public String getsJibun() {
		return sJibun;
	}
	public void setsJibun(String sJibun) {
		this.sJibun = sJibun;
	}
	public String getsAdd() {
		return sAdd;
	}
	public void setsAdd(String sAdd) {
		this.sAdd = sAdd;
	}
	public String getsPhone() {
		return sPhone;
	}
	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}
	public String getsSps() {
		return sSps;
	}
	public void setsSps(String sSps) {
		this.sSps = sSps;
	}
}
