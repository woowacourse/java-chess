package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	public void run() {

	}

	private String some() {
		try {
			return inputView.askCommand();
		}
		catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return some();
		}
	}
}
