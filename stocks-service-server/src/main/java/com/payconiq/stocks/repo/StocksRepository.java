package com.payconiq.stocks.repo;

import com.payconiq.stocks.entity.Stock;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository responsible for carrying CRUD operations regarding {@link Stock}.
 */
public interface StocksRepository extends CrudRepository<Stock, Long> {

	/**
	 * Retrieves a stock by the given name.
	 *
	 * @param name name of the stock
	 * @return {@link Optional#empty()} if there is no stock present for the given name
	 */
	Optional<Stock> findByName(String name);
}
