package com.javaex.vo;
import java.util.List;
public class OrderInfoVo {
	private int orderNo;
	private String orderDate;
	private int totalPrice;
	private int memNo;
	private String memId;
	private int orderComplete;
	private int orderCancel;
	private int count;
	private String proFileName;
	private String proName;
	private int proPrice;
	private int amount;
	private int proNo;
	public int getProNo() {
		return proNo;
	}
	public void setProNo(int proNo) {
		this.proNo = proNo;
	}

	private List<OrderInfoVo> list;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public List<OrderInfoVo> getList() {
		return list;
	}
	public void setList(List<OrderInfoVo> list) {
		this.list = list;
	}
	public String getProFileName() {
		return proFileName;
	}
	public void setProFileName(String proFileName) {
		this.proFileName = proFileName;
	}
	public int getOrderComplete() {
		return orderComplete;
	}
	public int getCount() {
		return count;
	}
	public OrderInfoVo() {
		super();
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setOrderComplete(int orderComplete) {
		this.orderComplete = orderComplete;
	}
	public int getOrderCancel() {
		return orderCancel;
	}
	public void setOrderCancel(int orderCancel) {
		this.orderCancel = orderCancel;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public OrderInfoVo(int orderNo) {
		super();
		this.orderNo = orderNo;
	}
	
	
	
	public OrderInfoVo(int orderNo,String proName, String orderDate, int proPrice, int orderComplete) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.proPrice = proPrice;
		this.proName = proName;
		this.orderComplete = orderComplete;
		
	}
	public OrderInfoVo(int orderNo,String proName, String orderDate, int proPrice, int orderComplete, int count , int amount,List<OrderInfoVo> list ) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.proPrice = proPrice;
		this.proName = proName;
		this.orderComplete = orderComplete;
		this.count = count;
		this.amount = amount;
		this.list = list;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getProPrice() {
		return proPrice;
	}
	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public OrderInfoVo(int totalPrice, int memNo) {
		super();
		this.totalPrice = totalPrice;
		this.memNo = memNo;
	}
	public OrderInfoVo(String date, int price) {
		// TODO Auto-generated constructor stub
		this.orderDate = date;
		this.totalPrice = price;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public OrderInfoVo(int no, String date, int price, int memno2, int ordercomplete, int cancel) {
		// TODO Auto-generated constructor stub
		this.orderNo = no;
		this.orderDate = date;
		this.totalPrice = price;
		this.memNo = memno2;
		this.orderComplete = ordercomplete;
		this.orderCancel = cancel;
	}

	public OrderInfoVo(int no, String date, String memid, String proName2, int price, int ordercomplete, int cancel) {
		// TODO Auto-generated constructor stub
		this.orderNo = no;
		this.orderDate = date;
		this.memId = memid;
		this.proName = proName2;
		this.totalPrice = price;
		this.orderComplete = ordercomplete;
		this.orderCancel = cancel;
	}


	public OrderInfoVo(int orderNo, String orderDate, String memId, int memNo, String proName, int proPrice, int orderComplete, int orderCancel) {
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.memNo = memNo;
		this.orderComplete = orderComplete;
		this.orderCancel = orderCancel;
		this.proName = proName;
		this.proPrice = proPrice;
		this.memId = memId;
	}
	
	public OrderInfoVo(String orderDate, int totalPrice, int orderComplete, int memNo) {
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.orderComplete = orderComplete;
		this.memNo = memNo;
	}
}