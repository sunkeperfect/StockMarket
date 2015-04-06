package com.stockmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stockmarket.Market;
import com.stockmarket.model.JsonResult;

@Controller
public class StockController {
	/**
	 * 获取所有股票列表
	 */
	@RequestMapping(value="/order/buy", method = RequestMethod.POST)
	public void getStockList(){
		JsonResult result = new JsonResult();

		
		result.setStatus(200);
		result.setMsg("操作成功");
		
		result.setValue(order);
		return result;
	}
}
