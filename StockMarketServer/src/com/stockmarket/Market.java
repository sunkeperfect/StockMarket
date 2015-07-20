package com.stockmarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.stockmarket.model.Order;
import com.stockmarket.model.Order.OrderType;

public class Market {

	private static Market sMarket;
	public static void init() {
		sMarket = new Market();
	}
	
	public static Market getInstance() {
		return sMarket;
	}
	
	private static final long CHECKOUT_INTERVAL = 5 * 1000L;//2 * 60 * 1000L;
	
	private final List<TradingCenter> mTradingCenters = new ArrayList<TradingCenter>();
	private IMarketDataSource mMarketDataSource = new TestMarketDataSource();
	private Timer mTimer;
	public Market() {
		loadTradingCenters();
		simulation();
	}
	
	public void buy(Order buyOrder) {
		buyOrder.setType(OrderType.Buy);
		TradingCenter tradingCenter = findTradingCenterBySN(buyOrder.getStockCode());
		if (null == tradingCenter) {
			System.out.printf("找不到股票代码为%s的公司 %n", buyOrder.getStockCode());
			System.out.println("-");
			return ;
		}
		tradingCenter.buy(buyOrder);
	}
	
	public void sell(Order sellOrder) {
		sellOrder.setType(OrderType.Sell);
		TradingCenter tradingCenter = findTradingCenterBySN(sellOrder.getStockCode());
		if (null == tradingCenter) {
			System.out.printf("找不到股票代码为%s的公司 %n", sellOrder.getStockCode());
			System.out.println("-");
			return ;
		}
		tradingCenter.sell(sellOrder);
	}
	
	public void shut() {
		mTimer.cancel();
	}
	/**
	 * 加载交易
	 */
	private void loadTradingCenters() {
		mTradingCenters.clear();
		List<TradingCenter> tradingCenters = mMarketDataSource.loadTradingCenters();
		if (null != tradingCenters) {
			mTradingCenters.addAll(tradingCenters);
		}
		
		for (TradingCenter tradingCenter : mTradingCenters) {
			System.out.println(tradingCenter.toSimpleString());
		}
		System.out.println("-");
		System.out.println("-");
		System.out.println("-");
		
		//TODO data list changed
	}
	/**
	 * 根据stockcode获取交易中心
	 * @param sn
	 * @return
	 */
	public TradingCenter findTradingCenterBySN(String sn) {
		for (TradingCenter tradingCenter : mTradingCenters) {
			if (tradingCenter.getCompany().getStockCode().equals(sn)) {
				return tradingCenter;
			}
		}
		return null;
	}
	
	/**
	 * 触发交易市场结算
	 */
	private void checkoutAtInterval() {
		for (TradingCenter tradingCenter : mTradingCenters) {
			tradingCenter.checkout();
		}
		System.out.println("-");
		System.out.println("-");
	}
	
	// >>>>>>>>>>>>>>>>>>>>
	// 模拟执行
	private void simulation() {
		mTimer = new Timer("market");
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				checkoutAtInterval();
			}
		}, 0, CHECKOUT_INTERVAL);
	}
	
//	public static void main(String[] args) {
//		final Market market = new Market();
//		Order buyOrder = new Order();
//		buyOrder.setStockCode("VIP");
//		buyOrder.setNumber(5000000000L);
//		buyOrder.setPrice(11.00f);
//		market.buy(buyOrder);
//	}

}
