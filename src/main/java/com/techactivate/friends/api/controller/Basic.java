package com.techactivate.friends.api.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class Basic {

	@GetMapping({ "/", "" })
	public HttpEntity<String> index() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new HttpEntity<String>("Hello World", responseHeaders);
	}

}
