package chess.controller;

import chess.domain.Position;
import chess.domain.ChessGame;
import chess.view.Command;
import chess.view.CommandDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	private final ChessGame service;

	public ChessController() {
		this.service = new ChessGame();
	}

	public void run() {
		OutputView.printInitialMessage();
		while(isRunCommandNotEnd()) {
			OutputView.printBoard(OutputRenderer.toBoardDto(service.getBoard()));
		}
	}

	private boolean isRunCommandNotEnd() {
		return ExceptionHandler.RetryIfThrowsException(() -> {
			CommandDto commandDto = readCommand();
			Command command = commandDto.getCommand();
			if (command == Command.START) {
				initialize();
			}
			if (command == Command.MOVE) {
				move(commandDto.getSourcePosition(), commandDto.getTargetPosition());
			}
			return command != Command.END;
		});
	}

	private CommandDto readCommand() {
		return ExceptionHandler.RetryIfThrowsException(() ->
			InputRenderer.toCommandDto(InputView.readCommand()));
	}

	private void initialize() {
		service.initialize();
	}

	private void move(Position source, Position target) {
		service.movePiece(source, target);
	}
}
