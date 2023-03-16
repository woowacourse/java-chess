package chess.controller;

import java.util.function.Supplier;

import chess.domain.Board;
import chess.domain.Position;
import chess.view.CommandDto;
import chess.view.InputRenderer;
import chess.view.OutputRenderer;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	private Board board;

	public void run(){
		initialize();
		move();
	}

	private void initialize() {
		OutputView.printInitialMessage();
		CommandDto commandDto = readCommand();
		if (commandDto.getCommand() == Command.START) {
			board = Board.create();
			OutputView.printBoard(OutputRenderer.toBoardDto(board.getBoard()));
		}
		//TODO: board 생성 전에 Command.MOVE가 들어오는 경우 대응 필요.
		// if (commandDto.getCommand() == Command.MOVE) {...}
	}

	private CommandDto readCommand() {
		return RetryIfThrowsException(() ->
			InputRenderer.toCommandDto(InputView.readCommand()));
	}

	private void move() {
		CommandDto commandDto = readCommand();
		while (commandDto.getCommand() == Command.MOVE) {
			Position sourcePosition = commandDto.getSourcePosition();
			Position targetPosition = commandDto.getTargetPosition();
			tryCatchStrategy(() -> {
				board.movePiece(sourcePosition, targetPosition);
				OutputView.printBoard(OutputRenderer.toBoardDto(board.getBoard()));
			});
			commandDto =  readCommand();
		}
	}

	private static <T> T RetryIfThrowsException(final Supplier<T> strategy) {
		T result = null;
		while (result == null) {
			result = tryCatchStrategy(strategy, null);
		}
		return result;
	}

	private static void tryCatchStrategy(final Runnable runnable) {
		try {
			runnable.run();
		} catch (IllegalArgumentException exception) {
			OutputView.printErrorMessage(exception.getMessage());
		}
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
