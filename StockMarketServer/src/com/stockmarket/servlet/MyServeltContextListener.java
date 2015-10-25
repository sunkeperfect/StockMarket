package com.stockmarket.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

import com.stockmarket.market.Market;

public class MyServeltContextListener extends ContextLoaderListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
//		System.out.println("启动" + event);
//		initWebApplicationContext(event.getServletContext());
//		Market.init();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
//		System.out.println("销毁" + event);
//         closeWebApplicationContext(event.getServletContext());
//		 // super.contextDestroyed(event);
//
//		Market.getInstance().shut();
	}

}
