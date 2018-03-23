package com.payconiq.stocks.controller;

import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.guard.PayconiqStocksGuard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stocks")
public class PayconiqStocksController {

	private final PayconiqStocksGuard payconiqStocksGuard;

	@Autowired
	public PayconiqStocksController(final PayconiqStocksGuard payconiqStocksGuard) {
		this.payconiqStocksGuard = payconiqStocksGuard;
	}

	/**
	 * A simple GET rest end point responsible for retrieving a collection of all the available stocks.
	 *
	 * @return a collection of {@link Stock}
	 */
	@GetMapping
	public Collection<Stock> getStocks() {
		return payconiqStocksGuard.getAllStocks();
	}

	/**
	 * A simple GET rest end point responsible for retrieving a stock for the given id.
	 *
	 * @param id id to be retrieved for
	 * @return a {@link Stock}
	 */
	@GetMapping("/{id}")
	public Stock getStock(@PathVariable("id") Long id) {
		return payconiqStocksGuard.getStockById(id);
	}

	/**
	 * A simple POST rest end point responsible for adding a new {@link Stock}.
	 *
	 * @param addRequest object to be added
	 * @return added {@link Stock}
	 */
	@PostMapping
	public Stock addArticle(@RequestBody Stock addRequest) {
		return payconiqStocksGuard.addStock(addRequest);
	}

	/**
	 * A simple PUT rest end point responsible for updating an existing {@link Stock}.
	 *
	 * @param updateRequest object to be updated
	 * @return updated {@link Stock}
	 */
	@PutMapping
	public Stock updateStock(@RequestBody Stock updateRequest) {
		return payconiqStocksGuard.updateStock(updateRequest);
	}
}
