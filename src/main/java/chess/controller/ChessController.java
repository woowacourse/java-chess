package chess.controller;

import java.util.NoSuchElementException;

import chess.converter.console.StringToCommandConverter;
import chess.controller.dto.BoardDto;
import chess.domain.board.ChessScore;
import chess.domain.board.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.state.Ready;
import chess.domain.state.GameState;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	public void run() {
		outputView.displayGameRule();
		GameState state = new Ready(BoardInitializer.generate());
		while (!state.isFinished()) {
			state = processOneTurn(state);
		}
	}

	private GameState processOneTurn(GameState state) {
		try {
			checkTurn(state);
			Command command = StringToCommandConverter.from(inputView.askCommand());
			state = state.proceed(command);
			outputView.displayChessBoard(new BoardDto(state.getBoard()));
			checkScore(state, command);
		} catch (IllegalArgumentException | IllegalStateException | NoSuchElementException exception){
			outputView.displayErrorMessage(exception);
		}
		return state;
	}

	private void checkTurn(GameState state) {
		if (state.isRunning()) {
			outputView.displayTurn(state.getColor());
		}
	}

	private void checkScore(GameState state, Command command) {
		if (command.isStatus()) {
			ChessScore chessScore = state.generateScore();
			outputView.displayScore(chessScore);
		}
	}
}
