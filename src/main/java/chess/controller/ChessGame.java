package chess.controller;

import chess.Board;
import chess.Command;
import chess.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
	private final Board board = Board.createInitialBoard();

	public void run() {
		OutputView.printGameIntro();
		Command command = Command.of(InputView.requestCommand());
		if (command.isStart()) {
			OutputView.printBoard(board);
		}

		while (Command.of(InputView.requestCommand()).isMove()) {
			Position startPosition = Position.of(InputView.requestPosition());
			Position endPosition = Position.of(InputView.requestPosition());
			board.move(startPosition, endPosition);
			OutputView.printBoard(board);
		}
	}
}
