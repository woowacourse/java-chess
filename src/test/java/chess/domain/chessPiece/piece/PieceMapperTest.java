package chess.domain.chessPiece.piece;

import org.junit.jupiter.api.Test;

class PieceMapperTest {
	@Test
	void blackPawn() {
		String name = "p";
		String position = "a2";
		PieceMapper.create(name, position);
	}

	@Test
	void WhitePawn() {
		String name = "P";
		String position = "a7";
		PieceMapper.create(name, position);
	}
}
