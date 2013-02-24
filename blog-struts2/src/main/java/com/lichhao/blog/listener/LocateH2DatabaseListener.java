package com.lichhao.blog.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocateH2DatabaseListener implements ServletContextListener {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String contextRealPath = sce.getServletContext().getRealPath("/");
		contextRealPath = contextRealPath.replace('\\', '/');
		logger.info("工程的根路径为：" + contextRealPath);
		InputStream ins = sce.getServletContext().getResourceAsStream(
				"/WEB-INF/classes/jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String location = prop.getProperty("jdbc.url", "");
		if (location.indexOf("{location}") != -1) {
			logger.info("替换前jdbc.url的值：" + location);
			location = location.replace("{location}", contextRealPath
					+ "WEB-INF/classes/h2");
			prop.setProperty("jdbc.url", location);
			logger.info("替换后jdbc.url的值：" + prop.getProperty("jdbc.url"));
		} else {
			logger.info("不需要替换jdbc.url的值");
		}

		try {
			prop.store(new FileOutputStream(new File(contextRealPath
					+ "/WEB-INF/classes/jdbc.properties")),
					"保存到jdbc.properties文件");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
