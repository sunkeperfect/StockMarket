package com.stockmarket.model;

import com.stockmarket.TradingCenter;

public class Stock {
	public Stock(TradingCenter trading){
		this.companyName=trading.getCompany().getName();
		this.marketValue=trading.getCompany().getStockTotal()*trading.getCurrentPrice();
		this.stockCode=trading.getCompany().getStockCode();
		this.currentStockPrice=trading.getCurrentPrice();
	}

	private String stockCode;
	private String companyName;
	private long currentStockPrice;
	/**
	 * 总市值
	 */
	private long marketValue;
	/**
	 * 浮动比率
	 */
	private float floatPercent;
	
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
	this.companyName = companyName;
	}
	public long getCurrentStockPrice() {
		return currentStockPrice;
	}
	public void setCurrentStockPrice(long currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}
	public long getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(long marketValue) {
		this.marketValue = marketValue;
	}
	public float getFloatPercent() {
		return floatPercent;
	}
	public void setFloatPercent(float floatPercent) {
		this.floatPercent = floatPercent;
	}
	
}
