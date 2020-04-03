package chess.domain.chessPiece.piece;

import org.junit.jupiter.api.Test;

class PieceNameMatcherTest {
	@Test
	void blackPawn() {
		String name = "p";
		String position = "a2";
		PieceNameMatcher.create(name, position);
	}

	@Test
	void WhitePawn() {
		String name = "P";
		String position = "a7";
		PieceNameMatcher.create(name, position);
	}
}
