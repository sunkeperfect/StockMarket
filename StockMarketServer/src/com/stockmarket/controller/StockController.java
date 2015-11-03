package com.stockmarket.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.stockmarket.market.Market;
import com.stockmarket.market.MarketDataProvide;
import com.stockmarket.market.TradingIntermediary;
import com.stockmarket.model.Company;
import com.stockmarket.model.JsonResult;
import com.stockmarket.model.Order;
import com.stockmarket.model.Stock;

@Controller
public class StockController {
	@Autowired
	Market market;
	/**
	 * 根据code股票列表
	 */
	@RequestMapping(value = "/stock/list", method = RequestMethod.GET)
	public @ResponseBody Object getStockList(String codes) {
		JsonResult result = new JsonResult();
		result.setCode(200);
		result.setMsg("操作成功");
		ArrayList<Stock> list=getStockListByCodes(codes);
		result.setData(list);
		return result;
	}
	/**
	 * 获取所有
	 */
	@RequestMapping(value="/stock/all",method=RequestMethod.GET)
	public @ResponseBody Object getAllStock(Company company){
		JsonResult<List<TradingIntermediary>> result=new JsonResult<List<TradingIntermediary>>();
		result.setCode(200);
		result.setMsg("成功");
		result.setData(market.getTradingCenters());
		return result;
	}
	/**
	 * 根据 code 获取股票
	 */
	public ArrayList<Stock> getStockListByCodes(String codes) {
		ArrayList<Stock> list = new ArrayList<Stock>();
		String[] array = codes.split(",");
		for (String code : array) {
			TradingIntermediary trading = market.findTradingCenterBySN(code);
			if (trading != null) {
				list.add(new Stock(trading));
			}
		}
		return list;
	}
	/**
	 * 测试
	 * 
	 */
	@RequestMapping(value="stock/test",method=RequestMethod.GET)
	@ResponseBody
	public Object getTest(){

		JsonResult result = new JsonResult();
		result.setCode(200);
		result.setMsg("操作成功");
		Order order = new Order();
		result.setData(order);
		return result ;
	}
	
}
