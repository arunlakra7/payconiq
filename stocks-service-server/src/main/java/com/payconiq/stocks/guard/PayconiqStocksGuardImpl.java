package com.payconiq.stocks.guard;

import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.security.AccessService;
import com.payconiq.stocks.service.PayconiqStocksService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
final class PayconiqStocksGuardImpl implements PayconiqStocksGuard {

	private final AccessService accessService;
	private final PayconiqStocksService payconiqStocksService;

	@Autowired
	PayconiqStocksGuardImpl(final AccessService accessService, final PayconiqStocksService payconiqStocksService) {
		this.accessService = accessService;
		this.payconiqStocksService = payconiqStocksService;
	}

	@Override
	public Collection<Stock> getAllStocks() {
		return payconiqStocksService.getAllStocks();
	}

	@Override
	public Stock getStockById(final Long id) {
		Assert.notNull(id, "Id to get stock for must not be null");
		accessService.validateStockId(id);
		return payconiqStocksService.getStockById(id);
	}

	@Override
	public Stock addStock(final Stock addRequest) {
		Assert.notNull(addRequest, "Stock to be added must not be null");
		Assert.isNull(addRequest.getId(), "Id of a new stock to be added must be null as it is auto generated");
		Assert.notNull(addRequest.getName(), "Name of a new stock to be added must not be null");
		Assert.notNull(addRequest.getCurrentPrice(), "Current price of a new stock to be added must not be null");
		accessService.validateStockName(addRequest.getName());
		return payconiqStocksService.addStock(addRequest);
	}

	@Override
	public Stock updateStock(final Stock updateRequest) {
		Assert.notNull(updateRequest, "Stock to be updated must not be null");
		Assert.notNull(updateRequest.getId(), "Id of a stock to be updated must not be null");
		accessService.validateStockId(updateRequest.getId());
		return payconiqStocksService.updateStock(updateRequest);
	}
}
