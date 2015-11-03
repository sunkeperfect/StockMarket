package com.stockmarket.market;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stockmarket.model.Order;
import com.stockmarket.model.Order.OrderType;

@Component
@Scope("singleton")
public class Market implements InitializingBean,DisposableBean {

//	public  void init() {
//		sMarket = new Market();
//	}
//	
//	public static Market getInstance() {
//		return sMarket;
//	}
	
	private static final long CHECKOUT_INTERVAL = 5 * 1000L;//2 * 60 * 1000L;
	
	private final List<TradingIntermediary> mTradingCenters = new ArrayList<TradingIntermediary>();
	@Autowired
	private MarketDataSource marketDataSource;

	private Timer mTimer;
	public Market() {
	}
	public void init(){
		loadTradingCenters();
	}
	/**
	 * 买入
	 * @param buyOrder
	 */
	public void buy(Order buyOrder) {
		buyOrder.setType(OrderType.Buy);
		TradingIntermediary tradingCenter = findTradingCenterBySN(buyOrder.getStockCode());
		if (null == tradingCenter) {
			System.out.printf("找不到股票代码为%s的公司 %n", buyOrder.getStockCode());
			System.out.println("-");
			return ;
		}
		tradingCenter.buy(buyOrder);
	}
	/**
	 * 卖出
	 * @param sellOrder
	 */
	public void sell(Order sellOrder) {
		sellOrder.setType(OrderType.Sell);
		TradingIntermediary tradingCenter = findTradingCenterBySN(sellOrder.getStockCode());
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
		List<TradingIntermediary> tradingCenters = marketDataSource.loadTradingCenters();
		if (null != tradingCenters) {
			mTradingCenters.addAll(tradingCenters);
		}
		
		for (TradingIntermediary tradingCenter : mTradingCenters) {
			System.out.println(tradingCenter.toSimpleString());
		}
		System.out.println("-");
		System.out.println("-");
		System.out.println("-");
		System.out.println(this.toString());
//		
//		//TODO data list changed
	}
	/**
	 * 根据stockcode获取交易中心
	 * @param sn
	 * @return
	 */
	public TradingIntermediary findTradingCenterBySN(String sn) {
		for (TradingIntermediary tradingCenter : mTradingCenters) {
			if (tradingCenter.getCompany().getStockCode().equals(sn)) {
				return tradingCenter;
			}
		}
		return null;
	}
	public List<TradingIntermediary> getTradingCenters(){
		System.out.println(this.toString());

		return mTradingCenters;
	}
	
	/**
	 * 触发交易市场结算
	 */
	private void checkoutAtInterval() {
		for (TradingIntermediary tradingCenter : mTradingCenters) {
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
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		init();
	}
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
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
