package com.stockmarket.market;

import com.stockmarket.model.Order;

public interface ITradingCenter {
	void sell(Order order);
	void buy(Order order);
}
