package com.iu.s6;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iu.trans.Transport;
import com.iu.trans.Trip;

@Controller
@RequestMapping(value="/trans/**")//	/trans/test, /trans/test/go
public class TransController {
	
	@Inject
	private Transport transport;
	@Inject
	private Trip trip;
	
	@RequestMapping(value="trip")
	public void go() {
		trip.go();
	}
	
	@RequestMapping(value="go")
	public void start() {
		transport.bus();
		System.out.println("=============");
		transport.subway();
	}
	
}
