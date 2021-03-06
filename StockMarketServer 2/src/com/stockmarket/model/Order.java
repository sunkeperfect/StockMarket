package com.stockmarket.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * 股票订单
 * 
 * @author yong01.yin
 *
 */
public class Order implements Serializable {

	public static enum OrderType {
		Buy, Sell
	}
	
	// 临时的ID生成器
	private static final AtomicLong sID = new AtomicLong(5000L);
	public Order() {
		this.id = String.valueOf(sID.getAndIncrement());
		this.createAt = System.currentTimeMillis();
	}

	/**
	 * 订单ID
	 */
	private String id;
	/**
	 * 交易数量
	 */
	private long number;
	/**
	 * 价格
	 */
	private long price;
	/**
	 * 股票编码
	 */
	private String stockCode;
	/**
	 * 订单类型
	 */
	private OrderType type;
	/**
	 * 订单创建时间
	 */
	private long createAt;
	private int userId;
	private int status;
	private String statusName;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
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

	public long getCreateAt() {
		return createAt;
	}

	public void setCreateAt(long createAt) {
		this.createAt = createAt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "Order [createAt=" + createAt + ", id=" + id + ", number="
				+ number + ", price=" + price + ", stockCode=" + stockCode
				+ ", type=" + type + "]";
	}
	
	public String toSimpleString() {
		return String.format("订单（%s——%s|%d|%.2f）", getId(),
				getStockCode(), getNumber(), getPrice());
	}

}
