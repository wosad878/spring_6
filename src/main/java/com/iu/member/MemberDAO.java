package com.iu.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE="memberMapper.";
	
	public int join(MemberDTO memberDTO) throws Exception{
		return sqlSession.insert(NAMESPACE+"join", memberDTO);
	}
	
	public MemberDTO idCheck(String id)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"idCheck", id);
	}
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"login", memberDTO);
	}
	public int update(MemberDTO memberDTO)throws Exception{
		return sqlSession.update(NAMESPACE+"update", memberDTO);
	}
	public int delete(String id) throws Exception{
		return sqlSession.delete(NAMESPACE+"delete", id);
	}
}
