package com.test.stockmarket.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * 股票订单
 * 
 * @author yong01.yin
 *
 */
public class Order {

	public static enum OrderType {
		Buy, Sell
	}

	private static AtomicLong sID = new AtomicLong(5000L);
	public Order() {
		this.id = String.valueOf(sID.getAndIncrement());
	}

	private String id;
	/**
	 * 交易数量
	 */
	private long number;
	/**
	 * 价格
	 */
	private float price;
	/**
	 * 股票编码
	 */
	private String stockCode;
	/**
	 * 订单类型
	 */
	private OrderType type;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", number=" + number + ", price=" + price
				+ ", stockCode=" + stockCode + ", type=" + type + "]";
	}

}
