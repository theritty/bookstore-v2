package com.bookstore.web.services;

import com.bookstore.exceptions.CustomException;
import com.bookstore.helper.ExternalApiCaller;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StoreService {

	private static ArrayList<Integer> pageMaxIds;

	@PostConstruct
	public void init() {
		pageMaxIds = new ArrayList<>();
		pageMaxIds.add(0);
	}

	public void initPageInformation(int sizeOfPages) {
		//initialize page list
		pageMaxIds = new ArrayList<>();
		for (int i = 0; i < sizeOfPages; i++) {
			pageMaxIds.add(0);
		}
	}

	private void updatePageArray(Map<String, Object> map, int page) {
		int sizeOfPages = Integer.parseInt((String) map.get("totalPages"))+1 ;

		//if list is empty initiliaze with zeros
		if(pageMaxIds.size() < sizeOfPages)
			initPageInformation(sizeOfPages);

		//set next page max id value in list
		pageMaxIds.set(
				(int) Math.floor(page/50)+1,
				Integer.parseInt(((String) map.get("nextPage")).split("&maxId=")[1].split("&")[0])
		);
	}

	public Map<String, Object> getProductsOfPage(int page) throws CustomException {

		//get paginated products from external api
		Map<String, Object> map = ExternalApiCaller.getInstance()
				.addApiParams("category","3920")
				.addApiParams("maxId", String.valueOf(pageMaxIds.get((int) Math.floor(page/50))))
				.setApiPath("/v1/paginated/items")
				.callApi();

		//update page array for further use
		updatePageArray(map, page);

		//update items list to place in a table
		List<Object> itemsList = (List<Object>) map.get("items");
		itemsList = itemsList.subList((page%50)*20,(page%50)*20+20);
		map.put("items", Lists.partition(itemsList, 4));

		//add necessary fields to map
		map.put("currentPage", page);
		map.put("pages",IntStream.range(1,  Integer.parseInt((String) map.get("totalPages"))+1 ).boxed().collect(Collectors.toList()));

		return map;
	}

}