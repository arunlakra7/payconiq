package com.payconiq.stocks.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.repo.StocksRepository;
import com.payconiq.stocks.security.AccessService;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PayconiqStocksServiceImplTest {

	// given
	private final Long id = 1L;
	private final Stock stock = Stock.builder().id(id).name("Stock").currentPrice(1000).lastUpdate(LocalDate.now()).build();

	@InjectMocks
	private PayconiqStocksServiceImpl service;
	@Mock
	private StocksRepository stocksRepository;
	@Mock
	private AccessService accessService;

	@Test
	public void whenValidId_thenStockShouldBeFound() {

		// when
		when(stocksRepository.findById(id)).thenReturn(Optional.of(stock));

		// then
		final Stock found = service.getStockById(id);
		assertThat(found).isEqualTo(stock);
	}

	@Test
	public void whenAddStockWithLastUpdateNull_thenStockShouldBeAddedWithCurrentDate() {

		// given
		final Stock addRequest = stock.toBuilder().id(null).lastUpdate(null).build();
		final Stock expectedStock = addRequest.toBuilder().lastUpdate(LocalDate.now()).build();

		// when
		service.addStock(addRequest);

		// then
		verify(stocksRepository).save(expectedStock);
	}

	@Test
	public void whenAddStockWithLastUpdatePresent_thenStockShouldBeAddedWithGivenDate() {

		// given
		final LocalDate givenLastUpdate = LocalDate.now().plusDays(10);
		final Stock addRequest = stock.toBuilder().id(null).lastUpdate(givenLastUpdate).build();
		final Stock expectedStock = addRequest.toBuilder().lastUpdate(givenLastUpdate).build();

		// when
		service.addStock(addRequest);

		// then
		verify(stocksRepository).save(expectedStock);
	}

	@Test
	public void whenUpdatedStockWithNoChaneInName_thenNoStockNameValidationRequired() {

		// when
		when(stocksRepository.findById(id)).thenReturn(Optional.of(stock));
		service.updateStock(stock);

		// then
		verify(stocksRepository, times(1)).save(stock);
		verify(accessService, never()).validateStockName(stock.getName());
	}

	@Test
	public void whenUpdatedStockWithChaneInName_thenStockNameValidationRequired() {

		// given
		final Stock updateRequest = stock.toBuilder().name("Stock2").build();

		// when
		when(stocksRepository.findById(id)).thenReturn(Optional.of(stock));
		service.updateStock(updateRequest);

		// then
		verify(accessService, times(1)).validateStockName(updateRequest.getName());
		verify(stocksRepository, times(1)).save(updateRequest);
	}
}
