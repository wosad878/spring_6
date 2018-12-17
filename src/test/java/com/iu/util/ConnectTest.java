package com.iu.util;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.iu.s6.AbstractTestCase;

public class ConnectTest extends AbstractTestCase {

	@Inject
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		assertNotNull(sqlSession);
	}

}
