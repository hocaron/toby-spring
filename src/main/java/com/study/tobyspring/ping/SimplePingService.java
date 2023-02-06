package com.study.tobyspring.ping;

import org.springframework.stereotype.Service;

@Service
public class SimplePingService implements PingService {

	@Override
	public String ping(String name) {
		return "ping " + name;
	}
}
