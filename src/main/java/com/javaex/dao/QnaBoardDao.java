package com.javaex.dao;

import java.util.ArrayList;
import com.javaex.vo.QnaBoardVo;

public interface QnaBoardDao {
	public int insert(QnaBoardVo vo);
	public int delete(int no);
	//public ArrayList<QnaBoardVo> getList();
	public ArrayList<QnaBoardVo> getList(String type, int page);
	public int getBoardCount();
	public int getBoardCount(String type);
	public int getNoAnswerCount();
	public QnaBoardVo getBoard(int no);
	public int update(QnaBoardVo vo);
	public ArrayList<QnaBoardVo> getNoAnswerList(int page);
	public ArrayList<QnaBoardVo> getNoAnswerL();
	public QnaBoardVo prevQna(int qnaNo);
	public QnaBoardVo nextQna(int qnaNo);
	public QnaBoardVo prevNoAnsQna(int qnaNo);
	public QnaBoardVo nextNoAnsQna(int qnaNo);
	public ArrayList<QnaBoardVo> getMyQList(int memno, int page);
	public int getMyQCount(int memno);
	public int getNewOrderCount();	//신규주문 갯수
	//public ArrayList<QnaBoardVo> getMyPqList(int page);

}
