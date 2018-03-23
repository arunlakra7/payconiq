package com.payconiq.stocks.repo;

import static org.assertj.core.api.Assertions.assertThat;

import com.payconiq.stocks.entity.Stock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StocksRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private StocksRepository stocksRepository;

	@Test
	public void whenFindByName_thenReturnStock() {

		// given
		final Stock stock = Stock.builder().name("stock").currentPrice(1000).build();
		testEntityManager.persist(stock);
		testEntityManager.flush();

		// when
		final Stock found = stocksRepository.findByName(stock.getName()).get();

		// then
		assertThat(found.getName()).isEqualTo(stock.getName());
	}
}
