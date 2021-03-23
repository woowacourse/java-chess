package chess.controller;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.board.Point;
import chess.view.OutputView;
import chess.view.InputView;

public class ChessController {
	public static final String STATUS = "status";
	public static final String MOVE = "move";
	public static final String END = "end";
	public static final String START = "start";

	public void run() {
		OutputView.printStartGuideMessage();
		while (isPlaying()) {
			playChessGame();
		}
	}

	private boolean isPlaying() {
		try {
			return InputView.inputStart();
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return InputView.inputStart();
		}
	}

	private void playChessGame() {
		ChessGame chessGame = new ChessGame();
		OutputView.printBoard(chessGame);
		playGame(chessGame);
		OutputView.noticeGameFinished();
	}

	private void playGame(ChessGame chessGame) {
		boolean flag = true;
		while(flag) {
			try {
				flag = isContinue(chessGame, makeInput());
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
				flag = isContinue(chessGame, makeInput());
			}
		}
	}

	private List<String> makeInput() {
		try {
			return InputView.inputMoveOrStatus();
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return InputView.inputMoveOrStatus();
		}
	}

	private boolean isContinue(ChessGame chessGame, List<String> input) {
		try {
			return makeScoreOrMove(chessGame, input);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private boolean makeScoreOrMove(ChessGame chessGame, List<String> userInput) {
		if(userInput.get(0).equals(END)) {
			return false;
		}
		if (userInput.get(0).equals(STATUS)) {
			OutputView.printScore(chessGame.calculateScore());
		}
		if (userInput.get(0).equals(MOVE)) {
			Point source = createPoint(userInput.get(1));
			Point target = createPoint(userInput.get(2));
			playTurn(chessGame, source, target);
			OutputView.printBoard(chessGame);
		}
		if (userInput.get(0).equals(START)) {
			playChessGame();
		}
		return true;
	}

	private Point createPoint(String userInput) {
		try {
			return Point.of(userInput);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private void playTurn(ChessGame chessGame, Point source, Point target) {
		try {
			chessGame.playTurn(source, target);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
