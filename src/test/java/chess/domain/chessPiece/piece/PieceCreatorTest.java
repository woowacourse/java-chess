package chess.domain.chessPiece.piece;

import org.junit.jupiter.api.Test;

class PieceCreatorTest {
	@Test
	void blackPawn() {
		String name = "p";
		String position = "a2";
		PieceCreator.create(name, position);
	}

	@Test
	void WhitePawn() {
		String name = "P";
		String position = "a7";
		PieceCreator.create(name, position);
	}
}
