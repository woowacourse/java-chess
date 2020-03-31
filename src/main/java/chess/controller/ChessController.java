package chess.controller;

import java.util.List;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.MoveInfo;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	private static final String START_COMMAND = "start";
	private static final String END_COMMAND = "end";
	private static final String MOVE_COMMAND = "move";
	private static final String STATUS_COMMAND = "status";
	private static final String DELIMITER = " ";
	private static final List<String> FIRST_COMMANDS = List.of(START_COMMAND, END_COMMAND);
	private static final List<String> PLAY_COMMANDS = List.of(MOVE_COMMAND, STATUS_COMMAND);
	private static final int COMMAND_INDEX = 0;

	private final ChessService service;
	private final Board board;
	private Team team;

	public ChessController(ChessService chessService, Board board, Team team) {
		this.service = chessService;
		this.board = board;
		this.team = team;
	}

	public void start() {
		OutputView.printInitialMessage();
		String input = InputView.inputStartOrEnd();

		if (!FIRST_COMMANDS.contains(input)) {
			throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
		}
		if (END_COMMAND.equals(input)) {
			exit();
		}

		OutputView.printBoard(board.getBoard());
	}

	private void exit() {
		System.exit(0);
	}

	public void playTurn() {
		String input = InputView.inputMoveOrStatus();
		List<String> infos = List.of(input.split(DELIMITER));

		if (!PLAY_COMMANDS.contains(infos.get(COMMAND_INDEX))) {
			throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
		}
		if (STATUS_COMMAND.equals(input)) {
			status();
		}

		move(input);
	}

	private void move(String input) {
		service.move(MoveInfo.of(input));

		OutputView.printBoard(board.getBoard());
		team = team.next();

		if (board.isEnd()) {
			exit();
		}
	}

	private void status() {
		Status status = Status.of(board);
		OutputView.printScore(status.toMap());
		OutputView.printWinner(status.getWinner());
		exit();
	}
}
