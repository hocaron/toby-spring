package com.study.tobyspring.ping;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PingDecorator implements PingService {

	private final PingService pingService;

	public PingDecorator(PingService pingService) {
		this.pingService = pingService;
	}

	@Override
	public String ping(String name) {
		return "*" + pingService.ping(name) + "*";
	}
}
