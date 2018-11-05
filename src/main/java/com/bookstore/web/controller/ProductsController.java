package com.bookstore.web.controller;

import com.bookstore.exceptions.CustomException;
import com.bookstore.web.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsController {

	@Autowired
	ProductsService productsService;

	@RequestMapping(
			value = "/getProductById2/{id}",
			method = RequestMethod.GET)
	public ModelAndView getProductById2(@PathVariable(value = "id") String id) throws CustomException {
		return new ModelAndView("product", "productObj", productsService.findById(Integer.parseInt(id)));
	}

}