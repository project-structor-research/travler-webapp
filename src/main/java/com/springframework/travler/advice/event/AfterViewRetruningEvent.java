package com.springframework.travler.advice.event;

import org.aspectj.lang.JoinPoint;
import org.springframework.context.ApplicationEvent;

public class AfterViewRetruningEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private JoinPoint joinPoint;

	public AfterViewRetruningEvent(Object source, JoinPoint joinPoint) {
		super(source);
		this.joinPoint = joinPoint;
	}

	public JoinPoint getJoinPoint() {
		return this.joinPoint;
	}
}
