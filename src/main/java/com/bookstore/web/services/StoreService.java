package com.bookstore.web.services;

import com.bookstore.dao.StoreDao;
import com.bookstore.model.Product;
import com.bookstore.model.StoreResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StoreService {

	private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

	@Autowired
	StoreDao storeDao;

	public StoreResponse getProductsOfPage(int page) {

			// Following gets the list of products for he given page containing the following fields: id, name, price, discount
			List<Product> result = storeDao.findProductsOfPage(page);


			// Gets the total number of products saved into db
			// Then finds the number of pages which will be shown on view
			int result2 = storeDao.findCountOfProducts();


			// Concatenates the information received by previous two commands and redirects to view.
			StoreResponse response = new StoreResponse(result,
					(int) Math.ceil(result2/20.0), (int) Math.ceil(result.size()/20.0), page);
			return response;

		}

}