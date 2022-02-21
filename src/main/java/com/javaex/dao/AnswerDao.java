package com.javaex.dao;



import com.javaex.vo.AnswerVo;


public interface AnswerDao {
	public int insert(AnswerVo vo);//등록 
	public int delete(int aNo);//삭제 -- update로 avail 변경
	public AnswerVo getAnswer(int qNo);//가져오기
	
}
