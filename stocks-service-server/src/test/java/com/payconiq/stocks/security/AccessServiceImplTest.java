package com.payconiq.stocks.security;

import static org.mockito.Mockito.when;

import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.exception.PayconiqException;
import com.payconiq.stocks.repo.StocksRepository;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AccessServiceImplTest {

	@InjectMocks
	private AccessServiceImpl service;

	@Mock
	private StocksRepository stocksRepository;

	@Test(expected = PayconiqException.class)
	public void whenNotValidId_thenThrowException() {
		final Long id = 1L;

		// when
		when(stocksRepository.findById(id)).thenReturn(Optional.empty());
		// then
		service.validateStockId(id);

	}

	@Test(expected = PayconiqException.class)
	public void whenNotUniqueName_thenThrowException() {
		final String stockName = "Stock";
		final Stock stockFound = Stock.builder().currentPrice(1000).name(stockName).build();

		// when
		when(stocksRepository.findByName(stockName)).thenReturn(Optional.of(stockFound));
		// then
		service.validateStockName(stockName);

	}

}
