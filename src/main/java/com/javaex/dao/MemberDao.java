package com.javaex.dao;

import java.util.ArrayList;

import com.javaex.vo.MemberVo;
import com.javaex.vo.OrderInfoVo;





public interface MemberDao {
	public int insert(MemberVo vo);

	public MemberVo getMember( String memId, String memPass );

	public MemberVo getMember(int memNo);

	public int update(MemberVo vo);
	
	ArrayList<OrderInfoVo> getList(String no);
	public ArrayList<OrderInfoVo> getList(int memNo);
	
	public int delete(MemberVo vo);
	
	public String idCheck(String memId); // // 정보가 맞는 회원정보를 리턴
	
	public int chanegepw(String pw, String name, String id);
	public String FindMember(String name, String phone);

	
}