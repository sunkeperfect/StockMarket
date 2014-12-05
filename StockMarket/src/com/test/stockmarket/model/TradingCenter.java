package com.test.stockmarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 交易中心
 * 
 * @author yong01.yin
 *
 */
public class TradingCenter {

	private final Company company;
	private final List<Order> buyList = new LinkedList<Order>();
	private final List<Order> sellList = new LinkedList<Order>();;
	
	public TradingCenter(Company company) {
		this.company = company;
	}
	
	public Company getCompany() {
		return this.company;
	}
	
	public synchronized void buy(Order order) {
		System.out.println(String.format("有人想要以单价%.2f元买入'%s'公司%d股",
				order.getPrice(), company.getName(), order.getNumber()));
		buyList.add(order);
		// 有人需要购买，我们就以升序排序
		buyMatch(order, sInc);
	}
	
	public synchronized void sell(Order order) {
		System.out.println(String.format("有人想要以单价%.2f元卖掉'%s'公司%d股",
				order.getPrice(), company.getName(), order.getNumber()));
		sellList.add(order);
		sellMatch(order);
	}
	
	private void buyMatch(Order order, Comparator<Order> comparator) {
		System.out.println("正在匹配---->" + order);
		Collections.sort(sellList, comparator);
		List<Order> result = null;
		for (Order sellItem : sellList) {
			if (order.getPrice() > sellItem.getPrice()) {
				if (null == result) {
					result = new ArrayList<Order>();
				}
				result.add(sellItem);
				if (sellItem.getNumber() >= order.getNumber()) {
					//TODO 如果低价的价格已经满足数量
					break;
				}
				continue;
			}
			break;
		}
		if (null != result) {
			System.out.println("Order交易完成---->" + order);
		}
	}
	
	private void sellMatch(Order order) {
		System.out.println("正在匹配---->" + order);
		Collections.sort(sellList, sDec);
		for (Order buyItem : buyList) {
			buyMatch(buyItem);
		}
	}
	
	// 降序
	private static final Comparator<Order> sInc = new Comparator<Order>() {
		@Override
		public int compare(Order o1, Order o2) {
			return Float.compare(o1.getPrice(), o2.getPrice());
		}
	};
	
	// 升序
	private static final Comparator<Order> sDec = new Comparator<Order>() {
		
		@Override
		public int compare(Order o1, Order o2) {
			return Float.compare(o1.getPrice(), o2.getPrice()) * (-1);
		}
	};
	
}
