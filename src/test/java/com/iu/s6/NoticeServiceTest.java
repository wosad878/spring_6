package com.iu.s6;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;

import com.iu.board.notice.NoticeDAO;
import com.iu.board.notice.NoticeDTO;
import com.iu.file.FileDTO;

public class NoticeServiceTest extends AbstractTestCase {

	@Inject
	private NoticeDAO ns;
	
	@Test
	public void test() throws Exception {
		/*NoticeDTO noticeDTO = new NoticeDTO();
		FileDTO fileDTO = new FileDTO();
		noticeDTO.setNum(71);
		noticeDTO.setTitle("T");
		noticeDTO.setWriter("T");
		noticeDTO.setContents("T");
		
		fileDTO.setNum(71);
		fileDTO.setFname(null);
		fileDTO.setOname("T");
		fileDTO.setKind("n");
		ns.insert(noticeDTO, fileDTO);*/
		assertNotNull(ns);
	}

}
