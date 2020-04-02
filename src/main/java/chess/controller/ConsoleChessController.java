package chess.controller;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.MoveInfo;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessController implements ChessController {
	private final ChessService service;
	private final Board board;
	private Team team;

	public ConsoleChessController(ChessService chessService, Board board, Team team) {
		this.service = chessService;
		this.board = board;
		this.team = team;
	}

	@Override
	public void start() {
		OutputView.printInitialMessage();
		Command command = Command.of(InputView.inputStartOrEnd());

		if (command.isNotFirst()) {
			throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
		}
		if (command.isEnd()) {
			exit();
		}

		OutputView.printBoard(board.getBoard());
	}

	private void exit() {
		System.exit(0);
	}

	@Override
	public void playTurn() {
		String input = InputView.inputMoveOrStatus();
		Command command = Command.of(input);

		if (command.isNotPlay()) {
			throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
		}
		if (command.isStatus()) {
			status();
		}

		move(input);
	}

	private void move(String input) {
		service.move(MoveInfo.of(input), team);
		team = team.next();

		OutputView.printBoard(board.getBoard());

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
