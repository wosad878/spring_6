package com.iu.board.memo;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iu.util.Pager;

@Repository
public class MemoDAO {

	@Inject
	private SqlSession session;
	private static final String NAMESPACE = "memoMapper.";
	
	public MemoDTO select(int num) throws Exception {
		return session.selectOne(NAMESPACE+"select", num);
	}
	
	public List<MemoDTO> list(Pager pager) throws Exception {
		return session.selectList(NAMESPACE+"list", pager);
	}
}
