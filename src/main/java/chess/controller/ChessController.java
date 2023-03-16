package chess.controller;

import java.util.function.Supplier;

import chess.domain.Board;
import chess.view.CommandDto;
import chess.view.InputRenderer;
import chess.view.OutputRenderer;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	public void run(){
		initialize();
	}

	private void initialize() {
		OutputView.printInitialMessage();
		CommandDto commandDto = readCommand();
		if (commandDto.getCommand() == Command.START) {
			Board board = Board.create();
			OutputView.printBoard(OutputRenderer.toBoardDto(board.getBoard()));
		}
		//TODO: board 생성 전에 Command.MOVE가 들어오는 경우 대응 필요.
		// if (commandDto.getCommand() == Command.MOVE) {...}
	}

	private CommandDto readCommand() {
		return RetryIfThrowsException(() ->
			InputRenderer.toCommandDto(InputView.readCommand()));
	}

	private static <T> T RetryIfThrowsException(final Supplier<T> strategy) {
		T result = null;
		while (result == null) {
			result = tryCatchStrategy(strategy, result);
		}
		return result;
	}

	private static <T> T tryCatchStrategy(final Supplier<T> strategy, T result) {
		try {
			result = strategy.get();
		} catch (IllegalArgumentException exception) {
			OutputView.printErrorMessage(exception.getMessage());
		}
		return result;
	}
}
