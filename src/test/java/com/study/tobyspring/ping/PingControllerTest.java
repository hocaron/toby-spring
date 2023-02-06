package com.study.tobyspring.ping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PingControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void controllerTest() throws Exception {

		this.mockMvc.perform(get("/ping")
				.param("name", "hocaron"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("*ping hocaron*"));
	}
}
