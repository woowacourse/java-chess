package chess.domain;

import java.util.function.Consumer;

import chess.factory.BoardFactory;

public class ChessGame {
	private final ChessBoard chessBoard;

	public ChessGame() {
		this.chessBoard = new ChessBoard(BoardFactory.createBoard());
	}

	public ChessBoard play(Menu menu, Consumer<Double> consumer) {
		if (menu.isMove()) {
			chessBoard.move(menu.getStartPosition(), menu.getTargetPosition());
		}

		// if (menu.isStatus()) {
		// 	consumer.accept(chessBoard.getWinScore());
		// }
		return this.chessBoard;
	}

}
