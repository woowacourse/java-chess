package chess.controller;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
	public void run() {
		Board board = new Board();
		OutputView.printGameIntro();

		while (board.isFinished()) {
			doCommand(board);
		}
	}

	private void doCommand(Board board) {
		try {
			Command command = Command.of(InputView.requestCommand());
			startGameIfCommandIsStart(board, command);
			movePieceIfCommandIsMove(board, command);
			showStatusIfCommandIsStatus(board, command);
			endGameIfCommandIsEnd(board, command);
		} catch (RuntimeException e) {
			OutputView.printErrorMessage(e.getMessage());
			doCommand(board);
		}
	}

	private void endGameIfCommandIsEnd(Board board, Command command) {
		if (command.isEnd()) {
			board.setGameEnd();
		}
	}

	private void showStatusIfCommandIsStatus(Board board, Command command) {
		if (command.isStatus()) {
			System.out.println(board.status());
		}
	}

	private void movePieceIfCommandIsMove(Board board, Command command) {
		if (command.isMove()) {
			Position startPosition = Position.of(InputView.requestPosition());
			Position endPosition = Position.of(InputView.requestPosition());
			board.move(startPosition, endPosition);
			OutputView.printBoard(board);
		}
	}

	private void startGameIfCommandIsStart(Board board, Command command) {
		if (command.isStart()) {
			board.start();
			OutputView.printBoard(board);
		}
	}
}
