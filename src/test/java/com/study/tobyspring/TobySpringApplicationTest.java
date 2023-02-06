package com.study.tobyspring;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.study.tobyspring.ping.PingController;
import com.study.tobyspring.ping.SimplePingService;

class TobySpringApplicationTest {
	@MockBean
	SimplePingService simplePingService;

	@Mock
	PingController pingController = new PingController(simplePingService);

	@Test
	void helloApi() {
		PingController pingController = new PingController(name -> name);
		String res = pingController.ping("hocaron");

		assertThat(res).isEqualTo("ping hocaron");
	}
}
