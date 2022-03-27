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

	public void run() {
		InputView.printCommandGuide();
		processStart();
		final Board board = new Board(InitialBoard.createBoard());
		OutputView.printBoard(board);
		List<String> inputCommand = InputView.requestCommand();

		while (!Command.isEnd(inputCommand.get(0))) {
			processCommand(board, inputCommand);
			if (board.isFinished()) {
				break;
			}
			inputCommand = InputView.requestCommand();
		}
		OutputView.printWinner(board.judgeWinner());
	}

	private void processStart() {
		List<String> inputCommand = InputView.requestCommand();
		if (!Command.isStart(inputCommand.get(0))) {
			throw new IllegalArgumentException(GAME_START_ERROR);
		}
	}

	private void processCommand(final Board board, final List<String> inputCommand) {
		if (Command.isStart(inputCommand.get(0))) {
			throw new IllegalArgumentException(ALREADY_GAME_START_ERROR);
		}
		if (Command.isMove(inputCommand.get(0))) {
			processMove(inputCommand, board);
		}
		if (Command.isStatus(inputCommand.get(0))) {
			processStatus(board);
		}
	}

	private void processMove(final List<String> inputCommand, final Board board) {
		Position source = PositionConvertor.to(inputCommand.get(1));
		Position target = PositionConvertor.to(inputCommand.get(2));
		board.move(source, target);
		OutputView.printBoard(board);
	}

	private void processStatus(final Board board) {
		double blackScore = board.calculateScore(Team.BLACK);
		double whiteScore = board.calculateScore(Team.WHITE);
		StatusResult result = new StatusResult(blackScore, whiteScore);
		OutputView.printScore(result);
	}
}
