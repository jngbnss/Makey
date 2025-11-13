package com.wootechco.Makey;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "my.secret-key=test-secret")
class MakeyApplicationTests {

	@Test
	void contextLoads() {
	}

}
