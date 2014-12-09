package com.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockmarket.Market;
import com.stockmarket.model.Order;

@Controller
public class OrderController {

//	@Autowired
//	private Market market;
	
	@RequestMapping(value="/order/buy", method = RequestMethod.POST)
	public @ResponseBody Order buy(String id, String stockMarket) {
		System.out.println("buy===>" + id);
		System.out.println("buy===>" + stockMarket);
//		market.buy(order);
		return new Order();
	}
	
	@RequestMapping(value="/order/sell", method = RequestMethod.POST)
	public @ResponseBody Order sell(@ModelAttribute("order") Order order) {
		System.out.println("sell===>" + order);
//		market.sell(order);
		return order;
	}
	
}
