package com.payconiq.stocks.exception;

/**
 * A simple custom exception class.
 */
public final class PayconiqException extends RuntimeException {

	private final PayconiqReason reason;

	public PayconiqException(final PayconiqReason reason, final String message) {
		super(message);
		this.reason = reason;
	}

	public PayconiqReason getReason() {
		return reason;
	}
}
