package com.payconiq.stocks.service;

import com.payconiq.stocks.entity.Stock;

import java.time.LocalDate;
import java.util.Collection;

/**
 * A service responsible for carrying operations related to the stocks.
 */
public interface PayconiqStocksService {

	/**
	 * Retrieves all the available stocks.
	 *
	 * @return a collection of {@link Stock}
	 */
	Collection<Stock> getAllStocks();

	/**
	 * Retrieves the stock present for the given id.
	 *
	 * @param id id to be retrieved for
	 * @return a {@link Stock}
	 */
	Stock getStockById(Long id);

	/**
	 * Adds a new {@link Stock}. {@link LocalDate#now()} is stored as the last update of stock if requested {@link Stock#lastUpdate} is null.
	 *
	 * @param addRequest object to be added
	 * @return added {@link Stock}
	 */
	Stock addStock(Stock addRequest);

	/**
	 * Updates an existing{@link Stock}. {@link LocalDate#now()} is stored as the last update of stock if requested {@link Stock#lastUpdate} is null.
	 *
	 * @param updateRequest object to be updated
	 * @return updated {@link Stock}
	 */
	Stock updateStock(Stock updateRequest);
}
