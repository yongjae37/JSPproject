package com.javaex.dao;

import java.util.ArrayList;

import com.javaex.vo.QnaAnswerVo;

public interface QnaAnswerDao {
	public ArrayList<QnaAnswerVo> getAnsList(int qnaNo);
	public int insertAns(QnaAnswerVo vo);
	public int deleteAns(int ansNo);
	//public int updateAns(QnaAnswerVo vo);

}
