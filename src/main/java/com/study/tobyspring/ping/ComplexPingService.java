package com.study.tobyspring.ping;

public class ComplexPingService implements PingService {

	public String ping(String name) {
		return "ping " + name;
	}
}
