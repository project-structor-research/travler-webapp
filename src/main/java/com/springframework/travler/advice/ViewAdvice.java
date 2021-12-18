package com.springframework.travler.advice;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.springframework.travler.advice.event.AfterViewRetruningEvent;
import com.springframework.travler.advice.event.AfterViewThrowingEvent;
import com.springframework.travler.advice.event.BeforeViewEvent;

public class ViewAdvice implements ApplicationEventPublisherAware {

	private static final Logger logger = LoggerFactory.getLogger(ViewAdvice.class);
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void beforeView(JoinPoint joinPoint) {
		try {
			BeforeViewEvent event = new BeforeViewEvent(this, joinPoint);
			this.applicationEventPublisher.publishEvent(event);
		} catch (Exception var3) {
			logger.error(var3.getMessage(), var3);
		}
	}

	public void afterViewReturning(JoinPoint joinPoint) {
		try {
			AfterViewRetruningEvent event = new AfterViewRetruningEvent(this, joinPoint);
			this.applicationEventPublisher.publishEvent(event);
		} catch (Exception var3) {
			logger.error(var3.getMessage(), var3);
		}

	}

	public void afterViewThrowing(JoinPoint joinPoint, Throwable ex) {
		try {
			AfterViewThrowingEvent event = new AfterViewThrowingEvent(this, joinPoint, ex);
			this.applicationEventPublisher.publishEvent(event);
		} catch (Exception var4) {
			logger.error(var4.getMessage(), var4);
		}

	}
}
