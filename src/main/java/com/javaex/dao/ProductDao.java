package com.javaex.dao;

import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.ProductVo;

public interface ProductDao {
	public int insert(ProductVo vo);		//새로운 상품 등록
	public int delete(int num);				//상품 삭제
	public int update(ProductVo vo);		//상품 정보 업데이트
	
	public ProductVo getProduct(int num);			//상품 상세정보
	public int getListCount();						//상품 갯수세기
	public int getListCount(String searchfrom, String kwd, String categ);		//상품 갯수세기
	public List<ProductVo> getList(int page, int limit, String searchFrom, String kwd, String orderBy, String categ);	//상품 목록
	
	public int getAdvListCount(String onSale, String categ, String name, String startPrice, String endPrice);		//상품 관리용 갯수세기
	public List<ProductVo> getAdvList(int page, int limit , String onSale, String categ, String name, String startPrice, String endPrice); //상품 관리용 목록
	
	public void updateHit(int num);			//상품 조회수 업데이트
	public int stockCheck(int proNo);		//재고확인
	
	public ArrayList<ProductVo> getList();	//추천상품 리스트
	public ArrayList<ProductVo> recommend(int id);	//추천상품
}
