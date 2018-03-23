package com.payconiq.stocks.guard;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.security.AccessService;
import com.payconiq.stocks.service.PayconiqStocksService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PayconiqStocksGuardImplTest {

	@InjectMocks
	private PayconiqStocksGuardImpl guard;

	@Mock
	private PayconiqStocksService service;

	@Mock
	private AccessService accessService;

	@Test
	public void getAllStocksTest() {

		// when
		guard.getAllStocks();

		// then
		verify(service, times(1)).getAllStocks();
	}

	@Test
	public void getStockByIdTest() {
		final Long id = 1L;

		// when
		guard.getStockById(id);

		// then
		verify(accessService, times(1)).validateStockId(id);
		verify(service, times(1)).getStockById(id);
	}

	@Test
	public void addStockTest() {
		final Stock addRequest = Stock.builder().name("Stock").currentPrice(1000).build();

		// when
		guard.addStock(addRequest);

		// then
		verify(accessService, times(1)).validateStockName(addRequest.getName());
		verify(service, times(1)).addStock(addRequest);
	}

	@Test
	public void updateStockTest() {

		final Stock updateRequest = Stock.builder().id(1L).name("Stock").currentPrice(1000).build();

		// when
		guard.updateStock(updateRequest);

		// then
		verify(accessService, times(1)).validateStockId(updateRequest.getId());
		verify(service, times(1)).updateStock(updateRequest);
	}

}
