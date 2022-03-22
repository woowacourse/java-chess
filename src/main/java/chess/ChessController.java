package chess;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	public void run() {
		if (inputView.askIfStart()) {
			outputView.displayChessBoard(new Board());
		}
	}
}
