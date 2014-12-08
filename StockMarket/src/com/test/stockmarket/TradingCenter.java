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
	static final Comparator<Order> sInc = new Comparator<Order>() {
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
	static final Comparator<Order> sDec = new Comparator<Order>() {
		@Override
		public int compare(Order o1, Order o2) {
			return Float.compare(o1.getPrice(), o2.getPrice()) * (-1);
		}
	};
	
	private final Company company;
	// 购买订单必须按照创建订单的顺序，以便再结算时按照顺序依次结算
	private final LinkedList<Order> buyList = new LinkedList<Order>();
	private final LinkedList<Order> sellList = new LinkedList<Order>();
	private final LinkedList<Order> matchedBuyList = new LinkedList<Order>();
	private final LinkedList<Order> matchedSellList = new LinkedList<Order>();
	
	public TradingCenter(Company company) {
		this.company = company;
	}
	
	public Company getCompany() {
		return this.company;
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

	/**
	 * 外部触发计算订单
	 */
	public synchronized void checkout() {
		new Processor().process(this);
	}
	
	private Order requestCreateSubOrder(Order buyOrder, Order sellOrder, long number) {
		Order subSellOrder = new Order();
		subSellOrder.setStockCode(sellOrder.getStockCode());
		subSellOrder.setType(sellOrder.getType());
		subSellOrder.setPrice(sellOrder.getPrice());
		subSellOrder.setNumber(number);
		// TODO 扣除子订单的数量
		sellOrder.setNumber(sellOrder.getNumber() - number);
		return subSellOrder;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// 内部的实现
	private class Processor {
		
		public Processor() {
		}
		
		public void process(TradingCenter tradingCenter) {
			List<Order> buyList = tradingCenter.getBuyList();
			
			// 判断特殊情况1：
			if (buyList.isEmpty()) {
				System.out.printf(
						"暂时没有买入'%s'公司股票的订单 %n",
						tradingCenter.getCompany().getName()
				);
				return ;
			}

			List<Order> sellList = tradingCenter.getSellList();
			// 判断特殊情况2：
			if (sellList.isEmpty()) {
				System.out.printf(
						"暂时没有出售'%s'公司股票的订单 %n",
						tradingCenter.getCompany().getName()
						);
				return ;
			}
			
			doProcessAfterCheck(tradingCenter);
		}
		
		private void doProcessAfterCheck(TradingCenter tradingCenter) {
			// copy
			ArrayList<Order> buyList = new ArrayList<Order>(tradingCenter.getBuyList());
			ArrayList<Order> sellList = new ArrayList<Order>(tradingCenter.getSellList());
			
			//TODO “卖出”股票的订单进行升序排序
			Collections.sort(sellList, TradingCenter.sInc);
			
			//TODO 根据订单的加入时间迭代获取“买入”订单，并匹配？？？NO
			Iterator<Order> buyListIte = buyList.iterator();//buyList.descendingIterator();
			while (buyListIte.hasNext())
				buyListIte : {
					Order buyItem = buyListIte.next();
					Result result = processBuyOrder(buyItem, sellList);
					
					// 综合处理结果
					switch (result.code) {
					case Result.END_MATCHED_ENOUGH:
						break;
					case Result.END_MATCHED_NOT_ENOUGH:
						// callback
						break;
					case Result.END_NO_SELL:
						break buyListIte;
					case Result.END_NO_MATCH:
						break;
					}
				}
		}
		
		private Result processBuyOrder(Order buyOrder, List<Order> sellList) {
			System.out.println("正在匹配---->" + buyOrder);
			
			// 在外部循环过程中，出售订单列表为空的情况
			if (sellList.isEmpty()) {
				System.out.println(String.format("暂时没有出售'%s'公司股票的订单", getCompany().getName()));
				return Result.obtain(Result.END_NO_SELL);
			}
			
			// 购买股票订单的价格不在出售价格的区间内
			//TODO 判断出售订单的最低价格是否已经高于购买价格
			Order firstSellOrder = sellList.get(0);
			if (firstSellOrder.getPrice() > buyOrder.getPrice()) {
				System.out.println(String.format("出售价格最低为%.2f，高于购买订单价格%.2f",
						firstSellOrder.getPrice(),
						buyOrder.getPrice()));
				return Result.obtain(Result.END_NO_MATCH);
			}
			
			Result result = Result.obtain(buyOrder, Result.END_NO_MATCH);
			// 购买股票订单的价格在出售价格的区间内
			for (Order sellItem : sellList) {
				//TODO 计算匹配的方案集合
				if (sellItem.getPrice() <= buyOrder.getPrice()) {
					matchCheaperSellOrder(buyOrder, sellItem, result);
				} else {
					matchExpensiveSellOrder(buyOrder, sellItem, result);
				}
				// end code
				if (result.isEndCode()) {
					return result ;
				}
			}
			
			return Result.obtain(Result.END_NO_MATCH);
		}
		
		private void matchCheaperSellOrder(Order buyOrder, Order sellOrder, Result result) {
			final long remaining = result.numberUnmatched();
			// 售出价格不高于买入价格，判断数量
			Order appendOrder = sellOrder;
			long number = Math.min(remaining, appendOrder.getNumber());
			boolean enough = (number >= remaining);
			boolean exactly = (number == remaining);
			
			if (enough) {
				result.code = Result.END_MATCHED_ENOUGH;
				if (exactly) {
					result.allMatchedSellOrders.add(appendOrder);
				} else {
					appendOrder = requestCreateSubOrder(buyOrder, appendOrder, number);
				}
			} else {
				result.code = Result.MATCHED_NOT_ENOUGH;
				result.allMatchedSellOrders.add(appendOrder);
			}
			
			result.matchedSellOrders.add(appendOrder);
			result.matchedNumber += appendOrder.getNumber();
			result.matchedTotalPrice += (appendOrder.getNumber() * appendOrder.getPrice());
		}
		
		private void matchExpensiveSellOrder(Order buyOrder, Order sellOrder, Result result) {
			final long remaining = result.numberUnmatched();
			// 售出价格高于买入价格，判断数量
			// 基于下面的不等式：
			// 	原来的总价 + number * 超过的单价
			// ————————————————————————————————  <= this.buyOrder.getPrice()
			// 		原来的总数量 + number
			float tpp = buyOrder.getPrice();
			float tp = result.matchedTotalPrice;
			float tn = result.matchedNumber;
			
			float kp = sellOrder.getPrice();
			final long maxNeededNumber = Math.min((long) ((tn * tpp - tp) / (kp - tpp)), sellOrder.getNumber());
			if (maxNeededNumber == 0) {
				result.code = Result.END_NO_MATCH;
				return;
			}
			
			// 售出价格不高于买入价格，判断数量
			Order appendOrder = sellOrder;
			long number = Math.min(remaining, maxNeededNumber);
			boolean enough = (number >= remaining);
			boolean exactly = (number == remaining);
			
			if (maxNeededNumber > remaining) {
				// 生成子订单
				appendOrder = requestCreateSubOrder(buyOrder, appendOrder, number);
				result.code = Result.END_MATCHED_ENOUGH;
			} else if (maxNeededNumber == remaining) {
				result.code = Result.END_MATCHED_ENOUGH;
				if (maxNeededNumber == appendOrder.getNumber()) {
					result.allMatchedSellOrders.add(appendOrder);
				} else {
					// maxNeededNumber < sellOrder.getNumber()
					// 生成子订单
					appendOrder = requestCreateSubOrder(buyOrder, appendOrder, number);
				}
			} else {
				result.code = Result.MATCHED_NOT_ENOUGH;
				result.allMatchedSellOrders.add(appendOrder);
			}
			
			result.matchedSellOrders.add(appendOrder);
			result.matchedNumber += appendOrder.getNumber();
			result.matchedTotalPrice += (appendOrder.getNumber() * appendOrder.getPrice());
		}
		
	}
	
	private static class Result {
		private static final int END_NO_SELL = -1;
		private static final int END_NO_MATCH = -2;
		private static final int END_MATCHED_ENOUGH = -3;
		private static final int END_MATCHED_NOT_ENOUGH = -4;
		private static final int MATCHED_NOT_ENOUGH = 1;
		
		public static Result obtain() {
			return new Result();
		}
		
		public static Result obtain(Order buyOder, int code) {
			Result result = new Result();
			result.buyOder = buyOder;
			result.code = code;
			return result;
		}
		
		public static Result obtain(int code) {
			Result result = new Result();
			result.code = code;
			return result;
		}
		
		public int code;
		public Order buyOder;
		private final List<Order> matchedSellOrders = new ArrayList<Order>();
		private final List<Order> allMatchedSellOrders = new ArrayList<Order>();
		private long matchedNumber;
		private float matchedTotalPrice;
		
		public boolean isEndCode() {
			return code < 0;
		}
		
		public boolean isNumberMatched() {
			return matchedNumber == buyOder.getNumber();
		}
		
		public long numberUnmatched() {
			return buyOder.getNumber() - matchedNumber;
		}
	}
	
	public String toSimpleString() {
		return "TradingCenter [buyList=" + buyList.size()
			+ ", sellList=" + sellList.size()
			+ ", company=" + company.getName()
			+ "]";
	}
	
	/*package*/ synchronized void setBuyList(List<Order> buyList) {
		this.buyList.clear();
		if (null != buyList) 
			this.buyList.addAll(buyList);
	}
	
	/*package*/ List<Order> getBuyList() {
		return this.buyList;
	}
	
	/*package*/ synchronized void setSellList(List<Order> sellList) {
		this.sellList.clear();
		if (null != sellList) 
			this.sellList.addAll(sellList);
	}
	
	/*package*/ List<Order> getSellList() {
		return this.sellList;
	}
	
	@Override
	public String toString() {
		return "TradingCenter [buyList=" + buyList + ", company=" + company
				+ ", sellList=" + sellList + "]";
	}
	
}
