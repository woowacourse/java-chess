package chess;

import chess.exception.SamePositionException;
import chess.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {
	private ChessBoard chessBoard;

	@BeforeEach
	void setUp() {
		chessBoard = new ChessBoard();
	}

	@Test
	void 같은_위치에_말_추가() {
		chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(2, 5)));
		assertThrows(SamePositionException.class, () ->
				chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(2, 5))));
	}

	@Test
	void 다른_위치에_말_추가() {
		chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(2, 5)));
		assertDoesNotThrow(() ->
				chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(3, 5))));
	}

	@Test
	void 해당_경로로_이동_불가능() {
		chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(6, 5)));
		Path path = new Path();
		path.add(Position.getPosition(5,5));
		path.add(Position.getPosition(6,5));
		path.add(Position.getPosition(7,5));
		assertFalse(chessBoard.isMovable(path));
	}

	@Test
	void 해당_경로로_이동_가능() {
		Path path = new Path();
		path.add(Position.getPosition(5,5));
		path.add(Position.getPosition(6,5));
		path.add(Position.getPosition(7,5));
		assertTrue(chessBoard.isMovable(path));
	}
}
