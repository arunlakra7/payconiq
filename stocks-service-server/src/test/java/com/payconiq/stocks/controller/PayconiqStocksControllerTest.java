package com.payconiq.stocks.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.guard.PayconiqStocksGuard;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PayconiqStocksController.class)
public class PayconiqStocksControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PayconiqStocksGuard guard;

	@Test
	public void givenStocks_whenGetStocks_ShouldReturnStocks() throws Exception {

		final Stock first = generateStock(1L);
		final Stock second = generateStock(2L);
		final Collection<Stock> expectedStocks = Lists.newArrayList(first, second);
		given(guard.getAllStocks()).willReturn(expectedStocks);

		mockMvc.perform(get("/api/stocks")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(expectedStocks.size())))
			.andExpect(jsonPath("$[0].id", is(first.getId().intValue())))
			.andExpect(jsonPath("$[0].name", is(first.getName())))
			.andExpect(jsonPath("$[0].currentPrice", is(first.getCurrentPrice())))
			.andExpect(jsonPath("$[0].lastUpdate", is(first.getLastUpdate().toString())))
			.andExpect(jsonPath("$[1].id", is(second.getId().intValue())))
			.andExpect(jsonPath("$[1].name", is(second.getName())))
			.andExpect(jsonPath("$[1].currentPrice", is(second.getCurrentPrice())))
			.andExpect(jsonPath("$[1].lastUpdate", is(second.getLastUpdate().toString())));

		verify(guard).getAllStocks();
		verifyNoMoreInteractions(guard);
	}

	@Test
	public void getById_ShouldReturnFoundStock() throws Exception {
		final Stock stock = generateStock(1L);

		given(guard.getStockById(stock.getId())).willReturn(stock);

		mockMvc.perform(get("/api/stocks/{id}", stock.getId())
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(stock.getId().intValue())))
			.andExpect(jsonPath("$.name", is(stock.getName())))
			.andExpect(jsonPath("$.currentPrice", is(stock.getCurrentPrice())))
			.andExpect(jsonPath("$.lastUpdate", is(stock.getLastUpdate().toString())));

		verify(guard, times(1)).getStockById(stock.getId());
		verifyNoMoreInteractions(guard);
	}

	@Test
	public void addStock_ShouldReturnAddedStock() throws Exception {
		final Stock addRequest = generateStock(1L).toBuilder().id(null).lastUpdate(null).build();
		final Stock addedStock = generateStock(1L);

		given(guard.addStock(addRequest)).willReturn(addedStock);

		mockMvc.perform(post("/api/stocks")
			.content(generateJSON(addRequest))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(addedStock.getId().intValue())))
			.andExpect(jsonPath("$.name", is(addedStock.getName())))
			.andExpect(jsonPath("$.currentPrice", is(addedStock.getCurrentPrice())))
			.andExpect(jsonPath("$.lastUpdate", is(addedStock.getLastUpdate().toString())));

		verify(guard, times(1)).addStock(addRequest);
		verifyNoMoreInteractions(guard);
	}

	@Test
	public void updateStock_ShouldReturnUpdatedStock() throws Exception {
		final Stock updateRequest = generateStock(1L).toBuilder().lastUpdate(null).build();
		final Stock updatedStock = generateStock(1L);

		given(guard.updateStock(updateRequest)).willReturn(updatedStock);

		mockMvc.perform(put("/api/stocks")
			.content(generateJSON(updateRequest))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(updatedStock.getId().intValue())))
			.andExpect(jsonPath("$.name", is(updatedStock.getName())))
			.andExpect(jsonPath("$.currentPrice", is(updatedStock.getCurrentPrice())))
			.andExpect(jsonPath("$.lastUpdate", is(updatedStock.getLastUpdate().toString())));

		verify(guard, times(1)).updateStock(updateRequest);
		verifyNoMoreInteractions(guard);
	}

	private Stock generateStock(final Long id) {
		return Stock.builder()
			.id(id)
			.name("Stock" + id)
			.currentPrice(1000 * id.intValue())
			.lastUpdate(LocalDate.now().plusDays(id))
			.build();
	}

	private String generateJSON(final Stock stock) throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(stock);
	}
}
