package com.test.stockmarket;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.test.stockmarket.model.Order;
import com.test.stockmarket.model.Order.OrderType;

public class Market {

	private static final long CHECKOUT_INTERVAL = 5 * 1000L;//2 * 60 * 1000L;
	
	private final List<TradingCenter> mTradingCenters = new ArrayList<TradingCenter>();
	private IMarketDataSource mMarketDataSource = new TestMarketDataSource();
	
	public void buy(Order buyOrder) {
		buyOrder.setType(OrderType.Buy);
		TradingCenter tradingCenter = findTradingCenterBySN(buyOrder.getStockCode());
		if (null == tradingCenter) {
			System.out.printf("找不到股票代码为%s的公司 %n", buyOrder.getStockCode());
			System.out.println("-");
			return ;
		}
		tradingCenter.buy(buyOrder);
		System.out.println("-");
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
		System.out.println("-");
	}
	
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
	
	private TradingCenter findTradingCenterBySN(String sn) {
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
		Timer timer = new Timer("market");
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				checkoutAtInterval();
			}
		}, 0, CHECKOUT_INTERVAL);
	}
	
	public static void main(String[] args) {
//		final Market market = new Market();
//		market.loadTradingCenters();
//		market.simulation();
//		
//		Order buyOrder = new Order();
//		buyOrder.setStockCode("VIP");
//		buyOrder.setNumber(5000000000L);
//		buyOrder.setPrice(11.00f);
//		market.buy(buyOrder);
		
		
		Enumeration<URL> urls;
		try {
			urls = Market.class.getClassLoader().getResources("com/test/");
			System.out.println(urls);
			while(urls.hasMoreElements()) {
				URL url = urls.nextElement();
				System.out.println(url);
				System.out.println(url.getAuthority());
				System.out.println(url.getFile());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
//		while(true) {
//			
//			try {
//				String code = reader.readLine();
//				if ("1".equals(code)) {
//					order = new Order();
//					TradingCenter tradingCenter1 = mTradingCenters.get(0);
//				} else if () {
//					
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		}
	}
	
}
