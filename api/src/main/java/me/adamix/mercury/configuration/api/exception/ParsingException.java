package me.adamix.mercury.configuration.api.exception;

public class ParsingException extends Exception {
	public ParsingException(String message) {
		super(message);
	}

	public ParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParsingException(Throwable cause) {
		super(cause);
	}
}
