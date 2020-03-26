package chess.domain;

import org.junit.jupiter.api.Test;

import chess.factory.BoardFactory;
import chess.view.OutputView;

public class ChessBoardTest {

	@Test
	void printTest() {
		ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());
		OutputView.printBoard(chessBoard);
	}
}
