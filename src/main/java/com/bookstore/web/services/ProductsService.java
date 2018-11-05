package com.bookstore.web.services;

import com.bookstore.exceptions.CustomException;
import com.bookstore.helper.ExternalApiCaller;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductsService {


	public Map<String, Object> findById(int id) throws CustomException {
		// Gets the detail of the current product by its id.

		Map<String, Object> map = getProductLookUp(id);
		// Gets the reviews of the current product
		Map<String, Object> map2 = getProductReviews(id);

		// Concatenates the information received by previous two commands and redirects to view.
		map.put("reviews", map2.get("reviews"));

		return map;
	}

	public Map<String, Object> getProductLookUp(int id) throws CustomException {
		//get products from external api
		Map<String, Object> map = ExternalApiCaller.getInstance()
				.addApiParams("format","json")
				.setApiPath("/v1/items/"+ id)
				.callApi();

		//add necessary fields
		map.put("numReviews", map.getOrDefault("numReviews", 0));
		map.put("msrp", map.getOrDefault("msrp", 0.0));

		return map;
	}

	public Map<String, Object> getProductReviews(int id) throws CustomException {
		//get reviews from external api
		Map<String, Object> map =  ExternalApiCaller.getInstance()
				.addApiParams("format","json")
				.setApiPath("/v1/reviews/"+ id)
				.callApi();


		return map;
	}

}