package com.javaex.dao;

import java.util.ArrayList;

import com.javaex.vo.PointVo;

public interface PointDao {
	public int insert(int memNo);//최초 회원가입
	public int insertProcess(int memNo, int payment , String pDesc);//결제,충전 등 모든 메커니즘
	public int check(int memNo);//유효성 검사
	public ArrayList<PointVo> getList(int memNo, int page);
	public int getPointCount(int memNo);
	public PointVo getPoint(int memNo);
	public int pointAfterReview(int memNo); //리뷰 작성하면 포인트 지급
	public int orderPoint(int orderNo, int memNo);//구입했을때 포인트지급
	public int pointAfterDelete(int memNo); //리뷰 삭제하면 포인트 차감
}
