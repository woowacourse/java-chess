package chess.controller;

import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

	public void run() {
		State state = new Ready();
		InputView.printCommandGuide();
		while (!state.isFinished()) {
			state = playCommand(state, requestCommand());
			BoardDto board = BoardDto.of(state.getBoard());
			OutputView.printBoard(board);
		}
		OutputView.printWinner(state.judgeWinner());
	}

	private List<String> requestCommand() {
		try {
			return InputView.requestCommand();
		} catch (RuntimeException runtimeException) {
			OutputView.printError(runtimeException.getMessage());
			return requestCommand();
		}
	}

	private State playCommand(final State state, final List<String> inputCommand) {
		try {
			Command command = Command.of(inputCommand);
			return command.apply(state, inputCommand);
		} catch (RuntimeException runtimeException) {
			OutputView.printError(runtimeException.getMessage());
			return playCommand(state, requestCommand());
		}
	}
}
