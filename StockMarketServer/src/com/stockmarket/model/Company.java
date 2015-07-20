package com.stockmarket.model;

/**
 * 公司模型
 * 
 * @author yong01.yin
 *
 */
public class Company {
	private int id;
	/**
	 * 股票名称
	 */
	private String name;
	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 股票数量
	 */
	private long stockTotal;
	/**
	 * 流通股数量
	 */
	private long circulatingStock;
	private String description;
	
	public Company(String name, String stockCode, long stockTotal,
			long circulatingStock) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", stockCode=" + stockCode
				+ ", stockTotal=" + stockTotal + ", circulatingStock="
				+ circulatingStock + "]";
	}

}
