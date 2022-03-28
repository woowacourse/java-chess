package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.InitialBoard;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.result.StatusResult;
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

	private Board board;

	public void run() {
		InputView.printCommandGuide();
		processStart(requestCommand());
		while (!board.isFinished()) {
			playCommand(requestCommand());
		}
		OutputView.printWinner(board.judgeWinner());
	}

	private void processStart(final List<String> inputCommand) {
		if (!Command.isStart(inputCommand.get(COMMAND_INDEX))) {
			throw new IllegalArgumentException(GAME_START_ERROR);
		}
		board = new Board(InitialBoard.createBoard());
		OutputView.printBoard(board);
	}

	private void processCommand(final List<String> inputCommand) {
		if (Command.isStart(inputCommand.get(COMMAND_INDEX))) {
			throw new IllegalArgumentException(ALREADY_GAME_START_ERROR);
		}
		if (Command.isMove(inputCommand.get(COMMAND_INDEX))) {
			processMove(inputCommand);
		}
		if (Command.isStatus(inputCommand.get(COMMAND_INDEX))) {
			processStatus(board);
		}
		if (Command.isEnd(inputCommand.get(COMMAND_INDEX))) {
			board.endGame();
		}
	}

	private void processMove(final List<String> inputCommand) {
		Position source = PositionConvertor.to(inputCommand.get(SOURCE_INDEX));
		Position target = PositionConvertor.to(inputCommand.get(TARGET_INDEX));
		board.move(source, target);
		OutputView.printBoard(board);
	}

	private void processStatus(final Board board) {
		double blackScore = board.calculateScore(Team.BLACK);
		double whiteScore = board.calculateScore(Team.WHITE);
		StatusResult result = new StatusResult(blackScore, whiteScore);
		OutputView.printScore(result);
	}

	private void playCommand(final List<String> inputCommand) {
		try {
			processCommand(inputCommand);
		} catch (RuntimeException runtimeException) {
			OutputView.printError(runtimeException.getMessage());
			playCommand(requestCommand());
		}
	}

	private List<String> requestCommand() {
		try {
			return InputView.requestCommand();
		} catch (RuntimeException runtimeException) {
			OutputView.printError(runtimeException.getMessage());
			return requestCommand();
		}
	}
}
