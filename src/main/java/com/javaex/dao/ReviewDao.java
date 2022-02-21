package com.javaex.dao;

import java.util.List;

import com.javaex.vo.ReviewVo;
public interface ReviewDao {
	
	public int reviewExists(int revNo, int memNo);	//리뷰가 있는지 확인
	public int writeAvail(int proNo, int memNo);	//리뷰 작성 가능 여부 확인
	public int updateAvail(int proNo, int memNo); //리뷰 수정 가능 여부 확인
	public int insert(ReviewVo vo);		//리뷰 작성
	public int update(ReviewVo vo);		//리뷰 수정 
	public int delete(int revNo);		//리뷰 삭제
	
	public List<ReviewVo> getList(int page, int limit,int proNo, String orderBy);	//리뷰 보여주기
	public int getListCount(int proNo);		//리뷰 갯수 세기
	public void updateHit(int revHit);		//조회수 올리기
	public float rateAvg(int proNum ); 		//별점 평균
	public ReviewVo getReview(int revNo);	//리뷰 찾기
	
	public List<ReviewVo> getPrivateList(int page, int limit, int memNo); 	//개인별 리뷰 리스트
	public int privateReviewCount(int memNo); 	//개인별 리뷰 리스트 갯수
	public int getAllListCount();	//전체 리뷰 갯수
	public List<ReviewVo> getAllList(int page, int limit);	//전체 리뷰 리스트
}

