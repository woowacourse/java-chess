package chess.controller;

import chess.Board;
import chess.Command;
import chess.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
	public void run() {
		Board board = new Board();
		OutputView.printGameIntro();

		while (board.checkGameEnd()) {
			Command command = Command.of(InputView.requestCommand());
			System.out.println(command);
			if (command.isStart()) {
				board.start();
				OutputView.printBoard(board);
			} else if (command.isMove()) {
				Position startPosition = Position.of(InputView.requestPosition());
				Position endPosition = Position.of(InputView.requestPosition());
				board.move(startPosition, endPosition);
				OutputView.printBoard(board);
			} else if (command.isStatus()) {
				System.out.println(board.status());
			} else if (command.isEnd()) {
				board.setGameEnd();
			}
		}
	}
}
