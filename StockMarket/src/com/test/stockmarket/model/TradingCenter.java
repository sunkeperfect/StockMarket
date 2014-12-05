package com.test.stockmarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 交易中心-模拟某个公司的买入卖出股票交易
 * 
 * @author yong01.yin
 *
 */
public class TradingCenter {

	private final Company company;
	private final LinkedList<Order> buyList = new LinkedList<Order>();
	private final LinkedList<Order> sellList = new LinkedList<Order>();;
	
	public TradingCenter(Company company) {
		this.company = company;
	}
	
	public Company getCompany() {
		return this.company;
	}
	
	public synchronized void setBuyList(List<Order> buyList) {
		this.buyList.clear();
		if (null != buyList) 
			this.buyList.addAll(buyList);
	}
	
	public List<Order> getBuyList() {
		return this.buyList;
	}
	
	public synchronized void setSellList(List<Order> sellList) {
		this.sellList.clear();
		if (null != sellList) 
			this.sellList.addAll(sellList);
	}
	
	public List<Order> getSellList() {
		return this.sellList;
	}
	
	public synchronized void buy(Order order) {
		System.out.println(String.format("有人想要以单价%.2f元买入'%s'公司%d股",
				order.getPrice(), company.getName(), order.getNumber()));
		buyList.add(order);
	}
	
	public synchronized void sell(Order order) {
		System.out.println(String.format("有人想要以单价%.2f元卖掉'%s'公司%d股",
				order.getPrice(), company.getName(), order.getNumber()));
		sellList.add(order);
	}

	public synchronized void checkout() {
		//TODO “卖出”股票的订单进行升序排序
		Collections.sort(sellList, Order.sInc);
		//TODO 根据订单的加入时间迭代获取“买入”订单，并匹配？？？NO
		Iterator<Order> ite = buyList.iterator();//buyList.descendingIterator();
		while (ite.hasNext()) {
			Order buyItem = ite.next();
			matchBuyOrder(buyItem);
		}
	}
	
	private void matchBuyOrder(Order buyOrder) {
		System.out.println("正在匹配---->" + buyOrder);
		List<Order> result = null;
		for (Order sellItem : sellList) {
			if (buyOrder.getPrice() > sellItem.getPrice()) {
				if (null == result) {
					result = new ArrayList<Order>();
				}
				result.add(sellItem);
				if (sellItem.getNumber() >= buyOrder.getNumber()) {
					//TODO 如果低价的价格已经满足数量
					break;
				}
				continue;
			}
			break;
		}
		if (null != result) {
			System.out.println("Order交易完成---->" + buyOrder);
		}
	}
	
	@Override
	public String toString() {
		return "TradingCenter [buyList=" + buyList + ", company=" + company
				+ ", sellList=" + sellList + "]";
	}
	
}
