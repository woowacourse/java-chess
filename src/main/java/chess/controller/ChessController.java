package chess.controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	public static void run() {
		OutputView.printGameStartInstruction();
		String decision = InputView.inputGameStartOrEnd();
		OutputView.printChessBoard(new Board());
	}
}
