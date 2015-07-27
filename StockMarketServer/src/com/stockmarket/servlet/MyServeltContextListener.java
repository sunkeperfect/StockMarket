package com.stockmarket.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.stockmarket.market.Market;

public class MyServeltContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("启动" + event);
		Market.init();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("销毁" + event);
		Market.getInstance().shut();
	}

}
