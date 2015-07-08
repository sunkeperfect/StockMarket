package com.stockmarket;

import java.util.List;

import com.stockmarket.model.Company;

public interface IMarketDataSource {

	/**
	 * 加载数据库所有交易中心
	 */
	List<TradingCenter> loadTradingCenters();
	
	/**
	 * 加载数据库公司信息
	 */
	List<Company> loadCompanies();
	
	/**
	 * 获取交易中心信息
	 * 
	 * @param tradingCenter 需要填充数据的交易中心对象
	 */
	void loadTradingCenter(TradingCenter tradingCenter);
	
}
