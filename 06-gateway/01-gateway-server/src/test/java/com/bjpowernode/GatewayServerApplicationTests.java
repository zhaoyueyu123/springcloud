package com.bjpowernode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
class GatewayServerApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(ZonedDateTime.now());
	}

}
