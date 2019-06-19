package chess;

import chess.exception.SamePositionException;
import chess.exception.UnmovableException;
import chess.piece.Piece;
import chess.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {
	private ChessBoard chessBoard;

	@BeforeEach
	void setUp() {
		chessBoard = new ChessBoard();
	}

	@Test
	void 같은_위치에_말_추가() {
		chessBoard.addPiece(Rook.valueOf(Player.BLACK, new Position(2, 5)));
		assertThrows(SamePositionException.class, () ->
				chessBoard.addPiece(Rook.valueOf(Player.BLACK, new Position(2, 5))));
	}

	@Test
	void 다른_위치에_말_추가() {
		chessBoard.addPiece(Rook.valueOf(Player.BLACK, new Position(2, 5)));
		assertDoesNotThrow(() -> chessBoard.addPiece(Rook.valueOf(Player.BLACK, new Position(3, 5))));
	}

	@Test
	void 체스_말_이동() {
		Piece blackRook = Rook.valueOf(Player.BLACK, new Position(1, 1));

		chessBoard.addPiece(blackRook);

		Position start = new Position(1, 1);
		Position end = new Position(1, 8);

		chessBoard.movePiece(Player.BLACK, start, end);

		assertTrue(blackRook.isSamePosition(new Position(1, 8)));
	}

	@Test
	void 움직이려는_말이_내_말이_아닌_경우() {
		Piece blackRook = Rook.valueOf(Player.BLACK, new Position(1, 1));
		Piece whiteRook = Rook.valueOf(Player.WHITE, new Position(1, 8));

		chessBoard.addPiece(blackRook);
		chessBoard.addPiece(whiteRook);

		Position start = new Position(1, 1);
		Position end = new Position(1, 8);

		assertThrows(UnmovableException.class, () ->
				chessBoard.movePiece(Player.WHITE, start, end));
	}

	@Test
	void 마지막_위치의_말이_내_말인_경우() {
		Piece blackRook = Rook.valueOf(Player.BLACK, new Position(1, 1));
		Piece whiteRook = Rook.valueOf(Player.BLACK, new Position(1, 8));

		chessBoard.addPiece(blackRook);
		chessBoard.addPiece(whiteRook);

		Position start = new Position(1, 1);
		Position end = new Position(1, 8);

		assertThrows(UnmovableException.class, () ->
				chessBoard.movePiece(Player.BLACK, start, end));
	}

	@Test
	void 상대방_말을_잡는_경우() {
		Piece blackRook = Rook.valueOf(Player.BLACK, new Position(1, 1));
		Piece whiteRook = Rook.valueOf(Player.WHITE, new Position(1, 8));

		chessBoard.addPiece(blackRook);
		chessBoard.addPiece(whiteRook);

		Position start = new Position(1, 1);
		Position end = new Position(1, 8);

		assertThat(chessBoard.movePiece(Player.BLACK, start, end)).isEqualTo(new Score(5));
	}
}
