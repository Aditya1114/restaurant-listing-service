package com.foodorder.restaurantlisting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("dev") // or "dev" or "local" depending on the profile you want to activate

class RestaurantlistingApplicationTests {

	@Test
	void contextLoads() {
	}

}
