package com.payconiq.stocks.security;

import com.payconiq.stocks.exception.PayconiqException;

/**
 * This service is responsible for validating the requests.
 */
public interface AccessService {

	/**
	 * Validates if the requested id is of one of the existing stocks.
	 *
	 * @param id id to be validated
	 * @throws PayconiqException with INVALID_ID as the reason if the given id does not exist
	 */
	void validateStockId(Long id);

	/**
	 * Validates stock name of a new stock to be added or updated.
	 *
	 * @param stockName stock name to be added
	 * @throws PayconiqException with STOCK_NAME_EXISTS as the reason if the name already exists
	 */
	void validateStockName(String stockName);
}
