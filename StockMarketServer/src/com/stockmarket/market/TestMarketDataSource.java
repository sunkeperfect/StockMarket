package com.stockmarket.market;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.stockmarket.model.Company;
import com.stockmarket.model.Order;
import com.stockmarket.model.Order.OrderType;

public class TestMarketDataSource implements IMarketDataSource {

	@Override
	public List<TradingCenter> loadTradingCenters() {
		List<TradingCenter> tradingCenters = new ArrayList<TradingCenter>();
		try {
			List<Company> companies = loadCompanies();
			for (Company company : companies) {
				TradingCenter tradingCenter = new TradingCenter(company);
				loadTradingCenter(tradingCenter);
				tradingCenters.add(tradingCenter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tradingCenters;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> loadCompanies() {
		try {
			Company[] companies = new Gson().fromJson(
					new InputStreamReader(Market.class.getClassLoader()
							.getResourceAsStream("com/stockmarket/companies.json")),
					Company[].class);
			return Arrays.asList(companies);
		} catch (Exception e) {
			e.printStackTrace();
			return (List<Company>) Collections.EMPTY_LIST;
		}
	}

	@Override
	public void loadTradingCenter(TradingCenter tradingCenter) {
		synchronized (tradingCenter) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						Market.class.getClassLoader().getResourceAsStream("com/stockmarket/test_sellorder.txt")));
				String line = null;
				while(null != (line = reader.readLine())) {
					String[] param = line.split("\\s+", 3);
					if (tradingCenter.getCompany().getStockCode().equals(param[0])) {
						Order sellOrder = new Order();
						sellOrder.setStockCode(param[0]);
						sellOrder.setNumber(Long.valueOf(param[1]));
						sellOrder.setPrice(Long.valueOf(param[2]));
						sellOrder.setType(OrderType.Sell);
						tradingCenter.getSellList().add(sellOrder);
					}
					
					try {
						Thread.sleep(5L);
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
