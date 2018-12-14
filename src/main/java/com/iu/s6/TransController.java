package com.iu.s6;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iu.trans.Transport;

@Controller
@RequestMapping(value="/trans/**")
public class TransController {
	
	@Inject
	private Transport transport;

	@RequestMapping(value="/go")
	public void start() {
		transport.bus();
	}
}
