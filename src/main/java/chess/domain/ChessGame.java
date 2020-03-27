package chess.domain;

import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
	private final ChessBoard chessBoard;
	private final Turn turn;
	private Menu menu;

	public ChessGame() {
		this.chessBoard = new ChessBoard(BoardFactory.createBoard());
		this.turn = new Turn();
	}

	public ChessBoard play() {
		OutputView.printRule();
		initMenu();
		while (menu.isNotEnd()) {
			proceed();
			OutputView.printBoard(chessBoard);
			initMenu();
		}
		OutputView.printRule();
		return this.chessBoard;
	}

	private void proceed() {
		if (menu.isMove()) {
			chessBoard.move(menu.getStartPosition(), menu.getTargetPosition());
			turn.changeTurn();
		}

		// if (menu.isStatus()) {
		// 	consumer.accept(chessBoard.getWinScore());
		// }
	}

	private void initMenu() {
		try {
			menu = new Menu(InputView.input());
		} catch (RuntimeException e) {
			OutputView.printErrorMessage(e);
			initMenu();
		}
	}

}
