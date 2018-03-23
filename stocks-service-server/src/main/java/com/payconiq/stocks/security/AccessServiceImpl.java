package com.payconiq.stocks.security;

import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.exception.PayconiqException;
import com.payconiq.stocks.exception.PayconiqReason;
import com.payconiq.stocks.repo.StocksRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
final class AccessServiceImpl implements AccessService {

	private final StocksRepository stocksRepository;

	@Autowired
	AccessServiceImpl(final StocksRepository stocksRepository) {
		this.stocksRepository = stocksRepository;
	}

	@Override
	public void validateStockId(final Long id) {
		Assert.notNull(id, "Id must not be null");
		final Optional<Stock> foundStock = stocksRepository.findById(id);
		if (!foundStock.isPresent()) {
			throw new PayconiqException(PayconiqReason.INVALID_ID, "Requested id : '" + id + "' is invalid");
		}
	}

	@Override
	public void validateStockName(final String stockName) {
		Assert.notNull(stockName, "Stock name must not be null");
		final Optional<Stock> foundStock = stocksRepository.findByName(stockName);
		if (foundStock.isPresent()) {
			throw new PayconiqException(PayconiqReason.STOCK_NAME_EXISTS, "Name : '" + stockName + "' of is not unique");
		}
	}
}
