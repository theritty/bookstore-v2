package com.bookstore;

import com.bookstore.config.SpringWebConfig;
import com.bookstore.web.services.StoreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SpringWebConfig.class)
public class StoreControllerTest {
	@Autowired
	private StoreService storeService;

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}


	@Test
	public void testMyMvcController() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		ResultMatcher msg = MockMvcResultMatchers.model()
				.attribute("storeResponse", storeService.getProductsOfPage(1));

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/products2");
		this.mockMvc.perform(builder)
				.andExpect(ok)
				.andExpect(msg);
	}


	@Test
	public void testMyMvcController2() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		ResultMatcher msg = MockMvcResultMatchers.model()
				.attribute("storeResponse", storeService.getProductsOfPage(2));

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/products2/2");
		this.mockMvc.perform(builder)
				.andExpect(ok)
				.andExpect(msg);
	}

}