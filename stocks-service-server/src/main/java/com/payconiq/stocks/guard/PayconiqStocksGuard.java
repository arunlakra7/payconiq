package com.payconiq.stocks.guard;

import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.service.PayconiqStocksService;

import java.util.Collection;

/**
 * This guard acts like a really thin layer to validate the requests before processing them further.
 */
public interface PayconiqStocksGuard {

	/**
	 * @see PayconiqStocksService#getAllStocks()
	 */
	Collection<Stock> getAllStocks();

	/**
	 * @see PayconiqStocksService#getStockById(Long) ()
	 */
	Stock getStockById(Long id);

	/**
	 * Adds a new {@link Stock}.
	 *
	 * @param addRequest object to be added
	 * @return added {@link Stock}
	 */
	Stock addStock(Stock addRequest);

	/**
	 * Updates an existing {@link Stock}
	 *
	 * @param updateRequest object to be updated
	 * @return updated {@link Stock}
	 */
	Stock updateStock(Stock updateRequest);
}
