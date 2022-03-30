package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.InitialBoard;
import chess.domain.board.Position;
import chess.domain.score.ScoreResult;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionConvertor;
import java.util.List;

public class ChessController {

	private static final String GAME_START_ERROR = "게임 시작을 먼저 해야 합니다.";
	private static final String ALREADY_GAME_START_ERROR = "게임이 이미 시작되었습니다.";
	private static final int COMMAND_INDEX = 0;
	private static final int SOURCE_INDEX = 1;
	private static final int TARGET_INDEX = 2;

	public void run() {
		State state = new Ready();
		InputView.printCommandGuide();
		state = processStart(state, requestCommand());
		while (!state.isFinished()) {
			state = playCommand(state, requestCommand());
		}
		OutputView.printWinner(state.judgeWinner());
	}

	private State processStart(final State state, final List<String> inputCommand) {
		Command command = parseCommand(inputCommand);
		if (!command.isStart()) {
			throw new IllegalArgumentException(GAME_START_ERROR);
		}
		Board board = new Board(InitialBoard.createBoard());
		OutputView.printBoard(board);
		return state.start(board);
	}

	private List<String> requestCommand() {
		try {
			return InputView.requestCommand();
		} catch (RuntimeException runtimeException) {
			OutputView.printError(runtimeException.getMessage());
			return requestCommand();
		}
	}

	private Command parseCommand(final List<String> inputCommand) {
		return Command.of(inputCommand.get(COMMAND_INDEX));
	}

	private State playCommand(final State state, final List<String> inputCommand) {
		try {
			return processCommand(state, inputCommand);
		} catch (RuntimeException runtimeException) {
			OutputView.printError(runtimeException.getMessage());
			return playCommand(state, requestCommand());
		}
	}

	private State processCommand(final State state, final List<String> inputCommand) {
		Command command = parseCommand(inputCommand);
		if (command.isStart()) {
			throw new IllegalArgumentException(ALREADY_GAME_START_ERROR);
		}
		if (command.isMove()) {
			return processMove(state, inputCommand);
		}
		if (command.isStatus()) {
			ScoreResult result = state.createStatus();
			OutputView.printScore(result);
			return state;
		}
		return state.finish();
	}

	private State processMove(final State state, final List<String> inputCommand) {
		Position source = PositionConvertor.to(inputCommand.get(SOURCE_INDEX));
		Position target = PositionConvertor.to(inputCommand.get(TARGET_INDEX));
		State movedState = state.play(source, target);
		OutputView.printBoard(movedState.getBoard());
		return movedState;
	}
}
