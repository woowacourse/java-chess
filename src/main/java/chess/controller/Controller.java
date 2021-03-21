package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Controller {
	private static final int COMMAND_INDEX = 0;

	public void run() {
		ChessBoard chessBoard = new ChessBoard();
		ChessGame chessGame = new ChessGame(chessBoard, Color.WHITE);
		OutputView.gameStart();
		if (InputView.isStart()) {
			startGame(chessGame);
			gameResult(chessGame);
			run();
		}
	}

	public void startGame(ChessGame chessGame) {
		chessGame.start();
		OutputView.printChessBoard(chessGame.getChessBoard());
		playGame(chessGame);
	}

	public void playGame(ChessGame chessGame) {
		while (chessGame.isOngoing()) {
			List<String> input = InputView.moveOrStatus();
			if (InputView.STATUS.equals(input.get(COMMAND_INDEX))) {
				break;
			}
			chessGame.run(input);
			OutputView.printChessBoard(chessGame.getChessBoard());
		}
	}

	public void gameResult(ChessGame chessGame) {
		Result result = chessGame.result();
		OutputView.printResult(result);
	}
}
