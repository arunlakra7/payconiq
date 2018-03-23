package com.payconiq.stocks.service;

import com.google.common.collect.Lists;
import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.repo.StocksRepository;
import com.payconiq.stocks.security.AccessService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
final class PayconiqStocksServiceImpl implements PayconiqStocksService {

	private final StocksRepository stocksRepository;
	private final AccessService accessService;

	@Autowired
	PayconiqStocksServiceImpl(final StocksRepository stocksRepository, final AccessService accessService) {
		this.stocksRepository = stocksRepository;
		this.accessService = accessService;
	}

	@Override
	public Collection<Stock> getAllStocks() {
		final Collection<Stock> result = Lists.newArrayList();
		stocksRepository.findAll().forEach(result::add);
		return result;
	}

	@Override
	public Stock getStockById(final Long id) {
		Assert.notNull(id, "Id must not be null");
		return stocksRepository.findById(id).get();
	}

	@Override
	public Stock addStock(final Stock addRequest) {
		Assert.notNull(addRequest, "Stock to be added must not be null");
		final LocalDate lastUpdate = determineLastUpdate(addRequest.getLastUpdate());
		final Stock stockToBeAdded = addRequest.toBuilder().lastUpdate(lastUpdate).build();
		return stocksRepository.save(stockToBeAdded);
	}

	@Override
	public Stock updateStock(final Stock updateRequest) {
		Assert.notNull(updateRequest, "Stock to be updated must not be null");
		final Stock oldStock = stocksRepository.findById(updateRequest.getId()).get();
		final LocalDate lastUpdate = determineLastUpdate(oldStock.getLastUpdate());
		final Stock updatedStock = updateRequest.toBuilder().lastUpdate(lastUpdate).build();
		if (oldStock.getName().equals(updateRequest.getName())) {
			return stocksRepository.save(updatedStock);
		}
		final String updatedName = updateRequest.getName();
		accessService.validateStockName(updatedName);
		return stocksRepository.save(updatedStock);
	}

	private LocalDate determineLastUpdate(@Nullable LocalDate requestedLastUpdate) {
		final Optional<LocalDate> lastUpdate = Optional.ofNullable(requestedLastUpdate);
		if (lastUpdate.isPresent()) {
			return lastUpdate.get();
		}
		return LocalDate.now();
	}

}
