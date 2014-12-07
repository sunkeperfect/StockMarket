package com.test.stockmarket;

import java.util.List;

import com.test.stockmarket.model.Company;

public interface IMarketDataSource {

	/**
	 * 加载公司信息
	 */
	List<Company> loadCompanies();
	
	/**
	 * 获取交易中心信息
	 * 
	 * @param tradingCenter 需要填充数据的交易中心对象
	 */
	void loadTradingCenter(TradingCenter tradingCenter);
	
}
