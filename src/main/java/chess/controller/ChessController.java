package chess.controller;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.board.Point;
import chess.view.OutputView;
import chess.view.InputView;

public class ChessController {
	public static final String STATUS = "status";
	public static final String MOVE = "move";

	public void run() {
		OutputView.printStartGuideMessage();

		while (InputView.inputStart()) {
			ChessGame chessGame = new ChessGame();
			OutputView.printBoard(chessGame);
			playGame(chessGame);
			OutputView.noticeGameFinished();
		}
	}

	private void playGame(ChessGame chessGame) {
		while (chessGame.isNotEnd()) {
			List<String> userInput = InputView.inputMoveOrStatus();
			makeScoreOrMove(chessGame, userInput);
		}
	}

	private void makeScoreOrMove(ChessGame chessGame, List<String> userInput) {
		if (userInput.get(0).equals(STATUS)) {
			OutputView.printScore(chessGame.calculateScore());
		}
		if (userInput.get(0).equals(MOVE)) {
			Point source = Point.of(userInput.get(1));
			Point target = Point.of(userInput.get(2));
			chessGame.playTurn(source, target);
			OutputView.printBoard(chessGame);
		}
	}
}
