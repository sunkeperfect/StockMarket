package com.test.stockmarket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.test.stockmarket.model.Company;
import com.test.stockmarket.model.Order;

/**
 * 交易中心-模拟某个公司的买入卖出股票交易
 * 
 * @author yong01.yin
 *
 */
public class TradingCenter {

	// 按照股票价格升序，同样的价格再按照创建时间早晚升序
	private static final Comparator<Order> sInc = new Comparator<Order>() {
		@Override
		public int compare(Order o1, Order o2) {
			int compare= Float.compare(o1.getPrice(), o2.getPrice());
			if (compare == 0) {
				compare = Long.signum(o1.getCreateAt() - o2.getCreateAt());
			}
			return compare;
		}
	};
	
	// 降序
	private static final Comparator<Order> sDec = new Comparator<Order>() {
		@Override
		public int compare(Order o1, Order o2) {
			return Float.compare(o1.getPrice(), o2.getPrice()) * (-1);
		}
	};
	
	private final Company company;
	// 购买订单必须按照创建订单的顺序，以便再结算时按照顺序依次结算
	private final LinkedList<Order> buyList = new LinkedList<Order>();
	private final LinkedList<Order> sellList = new LinkedList<Order>();;
	
	public TradingCenter(Company company) {
		this.company = company;
	}
	
	public Company getCompany() {
		return this.company;
	}
	
	synchronized void setBuyList(List<Order> buyList) {
		this.buyList.clear();
		if (null != buyList) 
			this.buyList.addAll(buyList);
	}
	
	List<Order> getBuyList() {
		return this.buyList;
	}
	
	synchronized void setSellList(List<Order> sellList) {
		this.sellList.clear();
		if (null != sellList) 
			this.sellList.addAll(sellList);
	}
	
	List<Order> getSellList() {
		return this.sellList;
	}
	
	public synchronized void buy(Order order) {
		System.out.println(String.format("有人想要以单价%.2f元买入'%s'公司%d股",
				order.getPrice(), getCompany().getName(), order.getNumber()));
		buyList.add(order);
	}
	
	public synchronized void sell(Order order) {
		System.out.println(String.format("有人想要以单价%.2f元卖掉'%s'公司%d股",
				order.getPrice(), getCompany().getName(), order.getNumber()));
		sellList.add(order);
	}

	public synchronized void checkout() {
		System.out.println(String.format("正在结算'%s'公司的股票交易中心", getCompany().getName()));
		if (sellList.isEmpty()) {
			System.out.println(String.format("暂时没有出售'%s'公司股票的订单", getCompany().getName()));
			return ;
		}
		//TODO “卖出”股票的订单进行升序排序
		Collections.sort(sellList, sInc);
		//TODO 根据订单的加入时间迭代获取“买入”订单，并匹配？？？NO
		Iterator<Order> ite = buyList.iterator();//buyList.descendingIterator();
		while (ite.hasNext()) {
			Order buyItem = ite.next();
			matchBuyOrder(buyItem);
		}
	}
	
	/**
	 * sellList 确认不为空列表，并且已经按照升序排列
	 */
	private void matchBuyOrder(Order buyOrder) {
		System.out.println("正在匹配---->" + buyOrder);
		// 1、 购买股票订单的价格不在出售价格的区间内
		//TODO 判断出售订单的最低价格是否已经高于购买价格
		Order firstSellOrder = sellList.get(0);
		if (firstSellOrder.getPrice() > buyOrder.getPrice()) {
			System.out.println(String.format("出售价格最低为%.2f，高于购买订单价格%.2f",
					firstSellOrder.getPrice(),
					buyOrder.getPrice()));
			return ;
		}
		
		TradingProcessor tradingProcessor = new TradingProcessor(buyOrder);
		// 2、购买股票订单的价格在出售价格的区间内
		for (Order sellItem : sellList) {
			//TODO 计算匹配的方案集合
			if (buyOrder.getPrice() >= sellItem.getPrice()) {
				if (null == tradingProcessor) {
					tradingProcessor = new TradingProcessor(buyOrder);
				}
				tradingProcessor.addMatchedOrder(sellItem);
				if (sellItem.getNumber() >= buyOrder.getNumber()) {
					//TODO 如果低价的价格已经满足数量
					break;
				}
				continue;
			} else {
				// 如果还没有匹配到，直接跳出循环
				if (null == tradingProcessor || tradingProcessor.isNumberMatched()) {
					break;
				}
			}
			break;
		}
		if (tradingProcessor.isNumberMatched()) {
			System.out.printf("订单（%s——%s|%s|%d|%.2f）交易完成 %n", 
					buyOrder.getId(),
					getCompany().getName(),
					getCompany().getStockCode(), 
					buyOrder.getNumber(), 
					buyOrder.getPrice());
		} else {
			System.out.printf("订单（%s——%s|%s|%d|%.2f）交易尚未完成，剩下%d股没有匹配 %n", 
					buyOrder.getId(),
					getCompany().getName(),
					getCompany().getStockCode(), 
					buyOrder.getNumber(), 
					tradingProcessor.numberUnmatched()
					);
		}
		
		tradingProcessor.deal();
	}
	
	private static class TradingProcessor {
		private static TradingProcessor sHead;
		public static TradingProcessor obtain(Order buyOrder, List<Order> sellList) {
			if (null == sHead) {
				sHead = new TradingProcessor(buyOrder, sellList);
			}
		}
		
		private TradingProcessor next;
		public void recycle() {
			this.buyOrder = null;
			this.matchedSellOrders.clear();
			this.mTradingStep = null;
			this.matchedNumber = 0L;
			sHead.next = this;
		}
		
		private Order buyOrder;
		private final List<Order> matchedSellOrders = new ArrayList<Order>();
		private TradingStepResult mTradingStep;
		private long matchedNumber;
		public TradingProcessor() {
			
		}
		
		private void reset(Order buyOrder, List<Order> sellList) {
			this.buyOrder = buyOrder;
			this.mTradingStep = new TradingStepResult();
			this.matchedNumber = 0L;
		}
		
		public void d() {
			
		}
		
		private TradingStepResult addMatchedOrder(Order sellOrder) {
			tryFinal: {
				if (isNumberMatched()) {
					break tryFinal;
				}
				long remaining = this.buyOrder.getNumber() - matchedNumber;
				if (sellOrder.getPrice() <= this.buyOrder.getPrice()) {
					// 售出价格不高于买入价格，判断数量
					if (sellOrder.getNumber() >= remaining) {
						
					} else {
						matchedSellOrders.add(sellOrder);
					}
				} else {
					
				}
			}
			return mTradingStep;
		}
		
		public void deal() {
			
		}
		
		public boolean isNumberMatched() {
			return buyOrder.getNumber() == matchedNumber;
		}
		
		public long numberUnmatched() {
			return buyOrder.getNumber() - matchedNumber;
		}
	}
	
	private static final int RES_NONE = 0;
	private static final int RES_START = RES_NONE + 1;
	private static final int RES_MATCHED_NOT_ENOUGH = RES_NONE + 2;
	private static final int RES_ENOUGH = RES_NONE + 3;
	private class TradingStepResult {
		public int type = RES_NONE;
		public Order buyOrder;
		public Order sellOrder;
	}
	
	@Override
	public String toString() {
		return "TradingCenter [buyList=" + buyList + ", company=" + company
				+ ", sellList=" + sellList + "]";
	}
	
	public String toSimpleString() {
		return "TradingCenter [buyList=" + buyList.size()
			+ ", sellList=" + sellList.size()
			+ ", company=" + company.getName()
			+ "]";
	}
	
}
