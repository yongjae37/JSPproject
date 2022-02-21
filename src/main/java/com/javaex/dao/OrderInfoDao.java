package com.javaex.dao;

import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.OrderInfoVo;

public interface OrderInfoDao {
	public int insert(OrderInfoVo vo);
	public int delete(int orderNo);
	public List<OrderInfoVo> getDetail(int orderNo);
	public List<OrderInfoVo> getList(int page , int limit, int memNo , String searchDate1, String searchDate2);
	public ArrayList<OrderInfoVo> getList1();
	public ArrayList<OrderInfoVo> getList2();
	public int getListCount(String searchDate1, String searchDate2 , int memNo);
	public int getOrderNo(int memNo);
	public OrderInfoVo recent(int memNo);
	public int change(int no);
	public ArrayList<OrderInfoVo> getList();
	public ArrayList<OrderInfoVo> getRecentList(int memNo);
	
}
