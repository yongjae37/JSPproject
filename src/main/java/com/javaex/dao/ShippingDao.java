package com.javaex.dao;

import com.javaex.vo.ShippingVo;

public interface ShippingDao {
	public int insert(ShippingVo vo);
	public int count1(int memNo); // 배송준비	
	public int count2(int memNo); // 배송 중
	public int count3(int memNo); // 배송 완료
}
