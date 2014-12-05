package com.test.stockmarket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.test.stockmarket.model.Company;
import com.test.stockmarket.model.Order;
import com.test.stockmarket.model.Order.OrderType;
import com.test.stockmarket.model.TradingCenter;

public class Market {

	private final List<TradingCenter> mTradingCenters = new ArrayList<TradingCenter>();
	
	private void loadTradingCenters() {
		try {
			Company[] companies = new Gson().fromJson(
					new InputStreamReader(Market.class.getClassLoader()
							.getResourceAsStream("com/test/stockmarket/companies.json")),
					Company[].class);
			for (Company company : companies) {
				System.out.println(company);
				TradingCenter tradingCenter = new TradingCenter(company);
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
	
	// >>>>>>>>>>>>>>>>>>>>
	// 模拟执行
	private void simulation() {
		// 测试卖出
		new Thread() {
			public void run() {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(
							Market.class.getClassLoader().getResourceAsStream("com/test/stockmarket/test.txt")));
					String line = null;
					while(null != (line = reader.readLine())) {
						String[] param = line.split("\\s+", 3);
						Order sellOrder = new Order();
						sellOrder.setStockCode(param[0]);
						sellOrder.setNumber(Long.valueOf(param[1]));
						sellOrder.setPrice(Float.valueOf(param[2]));
						sell(sellOrder);
					}
					System.out.println("-");
					System.out.println("-");
					System.out.println("-");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
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
