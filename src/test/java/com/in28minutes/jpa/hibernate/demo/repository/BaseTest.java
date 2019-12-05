package com.in28minutes.jpa.hibernate.demo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void log(String methodName, Object values) {
		logger.info("{}: {}", methodName, values);
	}
}
