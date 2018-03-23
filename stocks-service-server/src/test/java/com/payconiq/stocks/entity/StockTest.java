package com.payconiq.stocks.entity;

import com.payconiq.stocks.entity.Stock;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class StockTest {

	@Test
	public void testEqualsAndHashcode() {
		EqualsVerifier.forClass(Stock.class).allFieldsShouldBeUsed().verify();
	}
}
