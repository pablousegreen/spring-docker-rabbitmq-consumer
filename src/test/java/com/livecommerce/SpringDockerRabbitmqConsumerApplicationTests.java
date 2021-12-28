package com.livecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootTest
/*@ComponentScan(excludeFilters =
		{@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EncodeDecodeUtil.class)})*/
class SpringDockerRabbitmqConsumerApplicationTests {

	@Test
	void contextLoads() {
	}

}
