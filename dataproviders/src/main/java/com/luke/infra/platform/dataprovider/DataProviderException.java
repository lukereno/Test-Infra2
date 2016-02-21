package com.luke.infra.platform.dataprovider;

/**
 * This exception is used by data providers
 */
public class DataProviderException extends RuntimeException {

	private static final long serialVersionUID = 6146798143682025019L;

	public DataProviderException() {
		super();
	}

	public DataProviderException(String message) {
		super(message);
	}

	public DataProviderException(Throwable exception) {
		super(exception);
	}

	public DataProviderException(String message, Throwable exception) {
		super(message, exception);
	}

}
