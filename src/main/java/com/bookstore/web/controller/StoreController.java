package com.bookstore.web.controller;

import com.bookstore.exceptions.CustomException;
import com.bookstore.web.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StoreController {

	@Autowired
	StoreService storeService;

	@RequestMapping(
			value = "/store/{nextPage}",
			method = RequestMethod.GET)
	public ModelAndView redirectToTwitter(@PathVariable(value = "nextPage") String nextPage) throws CustomException {
		return new ModelAndView("store", "storeResponse", storeService.getProductsOfPage(Integer.parseInt(nextPage)));
	}

	@RequestMapping(
			value = "/store",
			method = RequestMethod.GET)
	public ModelAndView products2() throws CustomException {
		return new ModelAndView("store", "storeResponse", storeService.getProductsOfPage(1));
	}

}