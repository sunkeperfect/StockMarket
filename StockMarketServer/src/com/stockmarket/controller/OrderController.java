package com.stockmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockmarket.Market;
import com.stockmarket.model.JsonResult;
import com.stockmarket.model.Order;

@Controller
public class OrderController {

	@RequestMapping(value="/order/buy", method = RequestMethod.POST)
	public @ResponseBody JsonResult buy(@ModelAttribute("order") Order order) {
		JsonResult result = new JsonResult();
		System.out.println("buy stockCode===>" + order.getStockCode());
//		if (StringUtils.isEmpty(order.getStockCode())) {
//			return result;
//		}
		
		if (order.getNumber() <= 0) {
			result.setMsg("数量必须大于0");
			return result;
		}
		
		
		Market.getInstance().buy(order);
		
		result.setStatus(200);
		result.setMsg("操作成功");
		
		result.setValue(order);
		return result;
	}
	
	@RequestMapping(value="/order/sell", method = RequestMethod.POST)
	public @ResponseBody JsonResult sell(@ModelAttribute("order") Order order) {
		JsonResult result = new JsonResult();
		System.out.println("sell stockCode===>" + order.getStockCode());
//		if (StringUtils.isEmpty(order.getStockCode())) {
//			return result;
//		}
		
		if (order.getNumber() <= 0) {
			result.setMsg("数量必须大于0");
			return result;
		}
		
		Market.getInstance().sell(order);
		
		result.setStatus(200);
		result.setMsg("操作成功");
		
		result.setValue(order);
		return result;
	}
	
}
