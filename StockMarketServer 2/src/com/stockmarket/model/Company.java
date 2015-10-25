package com.stockmarket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 公司模型
 * 
 */
@Entity
public class Company {
	public Company(){
		
	}
	public Company(int id, String name, String stockCode, String numberCode, long stockTotal, long circulateStock,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.stockCode = stockCode;
		this.numberCode = numberCode;
		this.stockTotal = stockTotal;
		this.circulateStock = circulateStock;
		this.description = description;
	}

	@Id
	@GeneratedValue
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
	 * 股票数字代码
	 */
	private String numberCode;
	/**
	 * 股票数量
	 */
	private long stockTotal;
	/**
	 * 流通股数量
	 */
	private long circulateStock;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNumberCode() {
		return numberCode;
	}
	public void setNumberCode(String numberCode) {
		this.numberCode = numberCode;
	}
	public long getStockTotal() {
		return stockTotal;
	}
	public void setStockTotal(long stockTotal) {
		this.stockTotal = stockTotal;
	}
	public long getCirculateStock() {
		return this.circulateStock;
	}
	public void setCirculateStock(long circulateStock) {
		this.circulateStock = circulateStock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", stockCode=" + stockCode + ", numberCode=" + numberCode
				+ ", stockTotal=" + stockTotal + ", circulatingStock=" + circulateStock + ", description="
				+ description + "]";
	}


}
