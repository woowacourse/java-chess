package chess;

import chess.exception.SamePositionException;
import chess.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessBoardTest {
	private ChessBoard chessBoard;

	@BeforeEach
	void setUp() {
		chessBoard = new ChessBoard();
	}

	@Test
	void 같은_위치에_말_추가() {
		chessBoard.addPiece(Rook.valueOf(Player.BLACK, new Position(2,5)));
		assertThrows(SamePositionException.class, () ->
				chessBoard.addPiece(Rook.valueOf(Player.BLACK, new Position(2,5))));
	}

	@Test
	void 다른_위치에_말_추가() {
		chessBoard.addPiece(Rook.valueOf(Player.BLACK, new Position(2,5)));
		assertDoesNotThrow(() -> chessBoard.addPiece(Rook.valueOf(Player.BLACK, new Position(3,5))));
	}
}
