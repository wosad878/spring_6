package com.iu.board.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.util.Pager;

@Repository
public class NoticeDAO implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE="noticeMapper.";
	
	
	
	public int getNum()throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getNum");
	}
	
	
	
	@Override
	public int totalCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"totalCount", pager);
	}
	
	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		
		return sqlSession.selectList(NAMESPACE+"list", pager);
	}
	

	@Override
	public BoardDTO select(int num) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"select", num);
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"insert", boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"update", boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return sqlSession.delete(NAMESPACE+"delete", num);
	}

}
