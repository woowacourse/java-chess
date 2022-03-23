package chess.controller;

import chess.domain.board.Board;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	public void run() {
		Board board = new Board();
		InputView.printCommandGuide();
		while (!Command.isEnd(InputView.requestCommand())) {
			OutputView.printBoard(board);
		}
	}
}
