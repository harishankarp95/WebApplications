package com.hari.spring.drools.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hari.spring.drools.entity.Order;

@RestController
public class MegaOfferController {

	@Autowired
	private KieSession session;
	
	@PostMapping("/order")
	public Order orderNow(@RequestBody Order order) {
		System.out.println("------Inside orderNow()------");
		
		session.insert(order);
		session.fireAllRules();
		
		return order;
	}
}
