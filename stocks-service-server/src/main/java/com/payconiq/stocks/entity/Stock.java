package com.payconiq.stocks.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.Objects;

import java.time.LocalDate;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.Assert;

import lombok.Builder;

/**
 * A simple entity holding the attributes of a Stock.
 */
@Entity
@Immutable
@Table(name = "stocks")
@Builder(toBuilder = true)
@JsonPOJOBuilder(withPrefix = "")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "current_price", nullable = false)
	private Integer currentPrice;
	@Column(name = "last_update")
	private LocalDate lastUpdate;

	private Stock() {
	}

	public Stock(final Long id, final String name, final Integer currentPrice, final LocalDate lastUpdate) {
		Assert.notNull(name, "Name of the stock must not be null");
		Assert.notNull(currentPrice, "Current price of the stock must not be null");
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
		this.lastUpdate = lastUpdate;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getCurrentPrice() {
		return currentPrice;
	}

	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Stock that = (Stock) o;

		return Objects.equal(this.id, that.id) &&
			Objects.equal(this.name, that.name) &&
			Objects.equal(this.currentPrice, that.currentPrice) &&
			Objects.equal(this.lastUpdate, that.lastUpdate);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, currentPrice, lastUpdate);
	}
}
