package com.springframework.travler.advice.event;

import org.aspectj.lang.JoinPoint;
import org.springframework.context.ApplicationEvent;

public class AfterViewThrowingEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private JoinPoint joinPoint;
	private Throwable ex;

	public AfterViewThrowingEvent(Object source, JoinPoint joinPoint, Throwable ex) {
		super(source);
		this.joinPoint = joinPoint;
		this.ex = ex;
	}

	public JoinPoint getJoinPoint() {
		return this.joinPoint;
	}

	public Throwable getException() {
		return this.ex;
	}
}