package com.study.tobyspring.ping;

import java.util.Objects;

public class PingController {
	private final PingService pingService;

	public PingController(PingService pingService) {
		this.pingService = pingService;
	}

	public String ping(String name) {
		return pingService.ping(Objects.requireNonNull(name));
	}
}
