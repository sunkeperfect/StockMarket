package com.stockmarket.model;

import java.util.List;

/**
 * 
 * 成交订单
 * 
 * @author yong01.yin
 *
 */
public class TurnoverOrder {

	private Order sourceBuyOrder;
	
	private List<Order> sourceSellOrder;
	
	private long number;

	public Order getSourceBuyOrder() {
		return sourceBuyOrder;
	}

	public void setSourceBuyOrder(Order sourceBuyOrder) {
		this.sourceBuyOrder = sourceBuyOrder;
	}

	public List<Order> getSourceSellOrder() {
		return sourceSellOrder;
	}

	public void setSourceSellOrder(List<Order> sourceSellOrder) {
		this.sourceSellOrder = sourceSellOrder;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "TurnoverOrder [sourceBuyOrder=" + sourceBuyOrder
				+ ", sourceSellOrder=" + sourceSellOrder + ", number=" + number
				+ "]";
	}
	
}
