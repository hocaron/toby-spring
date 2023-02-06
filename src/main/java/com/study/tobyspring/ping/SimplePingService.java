package com.study.tobyspring.ping;

public class SimplePingService implements PingService {

	public String ping(String name) {
		return "ping " + name;
	}
}
