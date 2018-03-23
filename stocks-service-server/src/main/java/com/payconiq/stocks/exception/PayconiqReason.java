package com.payconiq.stocks.exception;

/**
 * An enum holding reasons for an exception.
 */
public enum PayconiqReason implements Reason {

	INVALID_ID,
	STOCK_NAME_EXISTS;

	@Override
	public String getCode() {
		return name();
	}
}
