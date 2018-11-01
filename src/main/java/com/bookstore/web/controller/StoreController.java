package com.bookstore.web.controller;

import com.bookstore.model.StoreResponse;
import com.bookstore.web.services.StoreService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class StoreController {

	@Autowired
	StoreService storeService;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView products() {
		StoreResponse storeResponse = storeService.getProductsOfPage(1);
		return new ModelAndView("store", "storeResponse", storeResponse);
	}


	@RequestMapping(value = "/products/{page}", method = RequestMethod.GET)
	public ModelAndView products(@PathVariable(value = "page") String page) {
		StoreResponse storeResponse = storeService.getProductsOfPage(Integer.parseInt(page));
		return new ModelAndView("store", "storeResponse", storeResponse);
	}

	@RequestMapping(
			value = "/products2/{maxId}",
			method = RequestMethod.GET)
	public void redirectToTwitter(@PathVariable(value = "maxId") String maxId)  {
		final String uri = "http://api.walmartlabs.com/v1/paginated/items?category=3920&maxId="+ maxId + "&apiKey=8dk7ncrp6bybdfh4ammsjkbm";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
	}

	@RequestMapping(
			value = "/products2",
			method = RequestMethod.GET)
	public ModelAndView products2() throws IOException {
		final String uri = "http://api.walmartlabs.com/v1/paginated/items?category=3920&maxId=0&apiKey=8dk7ncrp6bybdfh4ammsjkbm";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(result, new TypeReference<Map<String,Object>>() { });


		List<Object> itemsList = (List<Object>) map.get("items");
		map.put("items", Lists.partition(itemsList, 4));
		map.put("pages",IntStream.range(1,  Integer.parseInt((String) map.get("totalPages"))+1 ).boxed().collect(Collectors.toList()));

		return new ModelAndView("store", "storeResponse", map);
	}

}