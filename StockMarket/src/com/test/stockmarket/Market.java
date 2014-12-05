package com.test.stockmarket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.test.stockmarket.model.Company;
import com.test.stockmarket.model.Order;
import com.test.stockmarket.model.Order.OrderType;
import com.test.stockmarket.model.TradingCenter;

public class Market {

	private static final long CHECKOUT_INTERVAL = 2 * 60 * 1000L;
	
	private final List<TradingCenter> mTradingCenters = new ArrayList<TradingCenter>();
	
	private IMarketDataSource mMarketDataSource = new TestMarketDataSource();
	
	private void loadTradingCenters() {
		try {
			List<Company> companies = mMarketDataSource.loadCompanies();
			for (Company company : companies) {
				System.out.println(company);
				TradingCenter tradingCenter = new TradingCenter(company);
				mMarketDataSource.loadTradingCenter(tradingCenter);
				mTradingCenters.add(tradingCenter);
			}
			System.out.println("-");
			System.out.println("-");
			System.out.println("-");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private TradingCenter findTradingCenterBySN(String sn) {
		for (TradingCenter tradingCenter : mTradingCenters) {
			if (tradingCenter.getCompany().getStockCode().equals(sn)) {
				return tradingCenter;
			}
		}
		return null;
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
	
	/**
	 * 触发交易市场结算
	 */
	private void checkoutAtInterval() {
		for (TradingCenter tradingCenter : mTradingCenters) {
			tradingCenter.checkout();
		}
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
		final Market market = new Market();
		market.loadTradingCenters();
		market.simulation();
		
		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		Order buyOrder = new Order();
		buyOrder.setStockCode("VIP2");
		buyOrder.setNumber(500);
		buyOrder.setPrice(11.00f);
		market.buy(buyOrder);
		
		
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
