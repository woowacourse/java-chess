package chess.controller;

import java.util.List;
import java.util.Map;

import chess.Command;
import chess.domain.ChessGame;
import chess.domain.Status;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
	private static final String ERROR_MESSAGE_IMPOSSIBLE_COMMAND = "[ERROR] 지금은 앙댕! 혼난다??\n";

	private ChessGame game;

	public void run() {
		OutputView.announceStart();

		while (true) {
			inGame();
		}
	}

	private void inGame() {
		try {
			executeCommand(InputView.requireCommand2());
		} catch (IllegalArgumentException e) {
			OutputView.printMessage(e.getMessage());
		}
	}

	private void executeCommand(Map.Entry<Command, List<Square>> commands) {
		Command command = commands.getKey();
		if (command == Command.START) {
			start();
			return;
		}

		if (command == Command.MOVE) {
			move(commands);
			return;
		}

		if (command == Command.END) {
			System.exit(1);
		}

		if (command == Command.STATUS) {
			status();
			System.exit(1);
		}
	}

	private void start() {
		if (game != null) {
			throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
		}
		game = new ChessGame();
		OutputView.showBoard(game.getBoard());
		return;
	}

	private void move(Map.Entry<Command, List<Square>> commands) {
		checkGameStarted(game);
		if (game.isKingDie()) {
			throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
		}
		game.move(commands.getValue().get(0), commands.getValue().get(1));
		OutputView.showBoard(game.getBoard());
		return;
	}

	private void status() {
		checkGameStarted(game);
		if (!game.isKingDie()) {
			throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
		}
		Status status = game.saveStatus();
		OutputView.showScore(status, Color.WHITE);
		OutputView.showScore(status, Color.BLACK);
	}

	private void checkGameStarted(ChessGame game) {
		if (game == null) {
			throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
		}
	}

}
