package chess.controller;

import java.util.function.Supplier;

import chess.view.OutputView;

public class ExceptionHandler {

	public static <T> T RetryIfThrowsException(final Supplier<T> strategy) {
		T result = null;
		while (result == null) {
			result = tryCatchStrategy(strategy);
		}
		return result;
	}

	private static <T> T tryCatchStrategy(final Supplier<T> strategy) {
		try {
			return strategy.get();
		} catch (Exception exception) {
			OutputView.printErrorMessage(exception.getMessage());
		}
		return null;
	}
}
