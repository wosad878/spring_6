package com.iu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.iu.file.FileDTO;

public class FileDown extends AbstractView {
	
	public FileDown() {
		//setContentType("application/download;charset=UTF-8");
		
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		FileDTO fileDTO = (FileDTO)model.get("file");
		String realPath = request.getSession().getServletContext().getRealPath("/resources/notice");
		File file = new File(realPath, fileDTO.getFname());
		//한글 처리
		response.setCharacterEncoding(getContentType());
		//파일의 크기
		response.setContentLength((int)file.length());
		//oname을 UTF-8로 처리
		String fileName = URLEncoder.encode(fileDTO.getOname(), "UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream os = response.getOutputStream();
		FileInputStream fi = new FileInputStream(file);
		FileCopyUtils.copy(fi, os);
		fi.close();
		os.close();
		
	}
	
}
