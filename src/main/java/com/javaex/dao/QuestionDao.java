package com.javaex.dao;

import java.util.List;

import com.javaex.vo.AnswerVo;
import com.javaex.vo.QuestionVo;

public interface QuestionDao {
	public int insert(QuestionVo vo);//등록 
	public int delete(int qNo);//삭제 -- qavail 사용
	public int getListCount(int proNo);//리스트 갯수
	public List<QuestionVo> getList (int page, int limit, int proNo);//리스트
	public QuestionVo getQuestion(int qnum); //질문 가져오기 (for answer)
	public List<AnswerVo> getAnswerlist(int qNo);	//답변 리스트
	public List<QuestionVo> getMypqList(int memno, int page); //내가 쓴 상품문의 리스트
	public int getMypqCount(int memno); // 내가 쓴 상품문의 count
	public int allQlistCount();		//전체 질문 갯수
	public List<QuestionVo> allQlist (int page, int limit);		//전체 질문 리스트
	public int getNoAnsCount();
}
