package com.bookstore;

import com.bookstore.config.SpringWebConfig;
import com.bookstore.web.services.ProductsService;
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
public class ProductControllerTest {
	@Autowired
	private ProductsService productsService;

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
		ResultMatcher productObj = MockMvcResultMatchers.model()
				.attribute("productObj", productsService.findById(191483));

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/getProductById2/191483");
		this.mockMvc.perform(builder)
				.andExpect(ok)
				.andExpect(productObj);
	}

}