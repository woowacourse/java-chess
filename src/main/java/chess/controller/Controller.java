package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.piece.feature.Color;
import chess.view.InputView;
import chess.view.Option;
import chess.view.OutputView;

import java.util.List;

import static chess.view.InputView.COMMAND_INDEX;

public class Controller {
	public void run() {
		ChessBoard chessBoard = new ChessBoard();
		ChessGame chessGame = new ChessGame(chessBoard, Color.WHITE);
		OutputView.printMainScreen();
		if (InputView.isStart()) {
			startGame(chessGame);
			gameResult(chessGame);
			run();
		}
	}

	private void startGame(ChessGame chessGame) {
		chessGame.start();
		OutputView.printChessBoard(chessGame.getChessBoard());
		playGame(chessGame);
	}

	private void playGame(ChessGame chessGame) {
		while (chessGame.isOngoing()) {
			List<String> input = InputView.takeMoveOrStatusInput();
			if (Option.STATUS.getOption().equals(input.get(COMMAND_INDEX))) {
				break;
			}
			chessGame.movePiece(input);
			OutputView.printChessBoard(chessGame.getChessBoard());
		}
	}

	private void gameResult(ChessGame chessGame) {
		Result result = chessGame.result();
		OutputView.printResult(result);
	}
}
