package com.test.stockmarket.model;

/**
 * 公司模型
 * 
 * @author yong01.yin
 *
 */
public class Company {

	private String name;
	private String stockCode;
	/**
	 * 发行股数
	 */
	private long stockTotal;
	/**
	 * 流通股数量
	 */
	private long circulatingStock;

	public Company(String name, String stockCode, long stockTotal, long circulatingStock) {
		super();
		this.name = name;
		this.stockCode = stockCode;
		this.stockTotal = stockTotal;
		this.circulatingStock = circulatingStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public long getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(long stockTotal) {
		this.stockTotal = stockTotal;
	}

	public long getCirculatingStock() {
		return circulatingStock;
	}

	public void setCirculatingStock(long circulatingStock) {
		this.circulatingStock = circulatingStock;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", stockCode=" + stockCode + ", stockTotal="
				+ stockTotal + ", circulatingStock=" + circulatingStock + "]";
	}
	
}
