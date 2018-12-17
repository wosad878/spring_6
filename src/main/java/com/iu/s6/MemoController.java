package com.iu.s6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iu.board.memo.MemoDTO;
import com.iu.board.memo.MemoService;
import com.iu.util.Pager;

@RestController
@RequestMapping(value="/memo/**")
public class MemoController {
	
	@Inject
	private MemoService memoService;
	
	@RequestMapping("jtest2")
	public int jtest2() throws Exception {
		return 10;
	}
	
	@RequestMapping("jtest1")
	public Map<String, MemoDTO> jtest1() throws Exception {
		Map<String, MemoDTO> map = new HashMap<String, MemoDTO>();
		MemoDTO memoDTO = new MemoDTO();
		memoDTO.setNum(1);
		map.put("f1", memoDTO);
		memoDTO = new MemoDTO();
		memoDTO.setNum(2);
		map.put("f2", memoDTO);
		return map;
	}

	@RequestMapping("memoList")
	public void memoList() throws Exception {}
	
	@RequestMapping("list")
	public List<MemoDTO> list(Pager pager) throws Exception {
		return memoService.list(pager);
	}
	
	@RequestMapping("select")
	public MemoDTO select(int num) throws Exception {
		MemoDTO memoDTO = memoService.select(num);
		
		return memoDTO;
	}
}
