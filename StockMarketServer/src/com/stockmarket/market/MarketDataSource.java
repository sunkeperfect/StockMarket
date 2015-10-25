package com.stockmarket.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockmarket.model.Company;
import com.stockmarket.service.CompanyService;
@Component
public class MarketDataSource implements IMarketDataSource {
//	@Autowired
//	CompanyService companyService;
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
//			List<Company> companies = companyService.getCompanyList();
			List<Company> companies=new ArrayList<Company>();
			return companies;
		} catch (Exception e) {
			e.printStackTrace();
			return (List<Company>) Collections.EMPTY_LIST;
		}
	}

	@Override
	public void loadTradingCenter(TradingCenter tradingCenter) {
		synchronized (tradingCenter) {
			// try {
			// BufferedReader reader = new BufferedReader(new InputStreamReader(
			// Market.class.getClassLoader().getResourceAsStream("com/stockmarket/test_sellorder.txt")));
			// String line = null;
			// while(null != (line = reader.readLine())) {
			// String[] param = line.split("\\s+", 3);
			// if (tradingCenter.getCompany().getStockCode().equals(param[0])) {
			// Order sellOrder = new Order();
			// sellOrder.setStockCode(param[0]);
			// sellOrder.setNumber(Long.valueOf(param[1]));
			// sellOrder.setPrice(Long.valueOf(param[2]));
			// sellOrder.setType(OrderType.Sell);
			// tradingCenter.getSellList().add(sellOrder);
			// }
			//
			// try {
			// Thread.sleep(5L);
			// } catch (Exception e) {
			// }
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}
	}

}
