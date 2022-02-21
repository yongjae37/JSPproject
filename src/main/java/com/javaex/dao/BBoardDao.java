package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BBoardVo;


public interface BBoardDao {
	public int getListCount();		// 게시물 전체 갯수
	public List<BBoardVo> getList(int page, int limit);  // 게시물 전체 목록 조회
	public List<BBoardVo> getList2();  // 게시물 전체 목록 조회(상위 4개)
	public BBoardVo getBoard(int no); // 게시물 상세 조회
	public int insert(BBoardVo vo);   // 게시물 등록
	public int delete(int no);       // 게시물 삭제
	public BBoardVo prev(int no); // 이전글
	public BBoardVo after(int no); // 다음글
	
	
}
