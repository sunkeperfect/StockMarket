package com.stockmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.stockmarket.Market;
import com.stockmarket.model.JsonResult;
import com.stockmarket.model.Order;

@Controller
public class StockController {
	/**
	 * 获取所有股票列表
	 */
	@RequestMapping(value = "/stock/list", method = RequestMethod.GET)
	public Object getStockList() {
		JsonResult result = new JsonResult();
		result.setStatus(200);
		result.setMsg("操作成功");
		Order order = new Order();
		result.setValue(order);
		return result;
	}
	/**
	 * 测试
	 * 
	 */
	@RequestMapping(value="stock/test",method=RequestMethod.GET)
	@ResponseBody
	public Object getTest(){
		JsonResult result = new JsonResult();
		result.setStatus(200);
		result.setMsg("操作成功");
		Order order = new Order();
		result.setValue(order);
		return result ;
	}
	
}
