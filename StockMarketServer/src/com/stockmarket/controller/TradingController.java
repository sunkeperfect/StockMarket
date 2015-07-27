package com.stockmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockmarket.market.Market;
import com.stockmarket.market.TradingCenter;
import com.stockmarket.model.JsonResult;

@Controller
public class TradingController {
	/**
	 * http://localhost:8080/StockMarketServer/trading/list?stock_code=VIP
	 * 查询股票盘口列表
	 */
	@RequestMapping(value = "trading/list" ,method=RequestMethod.GET)
	public @ResponseBody JsonResult orderListByCode(String stock_code) {
		JsonResult result = new JsonResult();
		TradingCenter tradingCenter = Market.getInstance().findTradingCenterBySN(stock_code);
		if (tradingCenter == null) {
			result.setCode(200);
			result.setMsg("没有这个公司!");
			return result;
		}
		result.setCode(200);
		result.setMsg("成功");
		result.setData(tradingCenter.getTradingList());
		return result;
	}
}
