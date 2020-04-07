package chess.controller;

import chess.domain.position.MoveInfo;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessController implements ChessController {
	private static final String CONSOLE_ID = "console";

	private final ChessService service;

	public ConsoleChessController(ChessService chessService) {
		this.service = chessService;
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

		service.initialize(CONSOLE_ID);
		OutputView.printBoard(service.getBoard(CONSOLE_ID));
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
		service.move(CONSOLE_ID, MoveInfo.of(input));
		OutputView.printBoard(service.getBoard(CONSOLE_ID));
	}

	private void status() {
		OutputView.printScore(service.getResult(CONSOLE_ID));
		exit();
	}
}
