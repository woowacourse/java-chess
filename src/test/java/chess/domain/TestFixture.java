package chess.domain;

import static chess.domain.Side.*;

import java.util.Arrays;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;

public class TestFixture {
	public static Position D4 = new Position("d4");
	public static Position D5 = new Position("d5");
	public static Position D6 = new Position("d6");
	public static Position D3 = new Position("d3");
	public static Position D2 = new Position("d2");

	public static Position C3 = new Position("c3");
	public static Position C4 = new Position("c4");
	public static Position C5 = new Position("c5");
	public static Position E3 = new Position("e3");
	public static Position E4 = new Position("e4");
	public static Position E5 = new Position("e5");

	public static Bishop BISHOP_D4 = new Bishop(WHITE, D4);
	public static King KING_D4 = new King(WHITE, D4);
	public static Knight KNIGHT_D4 = new Knight(WHITE, D4);
	public static Pawn PAWN_D4 = new Pawn(WHITE, D4);
	public static Pawn PAWN_D2 = new Pawn(WHITE, D2);
	public static Queen QUEEN_D4 = new Queen(WHITE, D4);
	public static Rook ROOK_D4 = new Rook(WHITE, D4);

	public static Board PawnTestingBoard() {
		return new Board(Arrays.asList(
				PAWN_D4,
				new Pawn(WHITE, D5)
		));
	}
}
