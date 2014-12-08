package com.test.stockmarket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.test.stockmarket.model.Order;

public class IntervalTradingProcessor {
	
	public static interface Callback {
		
		void onSellOrderConsumed(Order sellOrder, Order buyOrder, long number) ;
		
		void onBuyOrderMatched(Order buyOrder, List<Order> allConsumedSellOrders,
				List<Order> consumedSellOrders) ;
		
	}


	
}
