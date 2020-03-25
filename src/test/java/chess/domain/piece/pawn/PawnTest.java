package chess.domain.piece.pawn;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;

class PawnTest {

	@Test
	void moveTo_When_Success() {
		Piece pawn = new Pawn(C2);
		pawn.moveTo(C4);
		pawn.moveTo(C5);
		pawn.moveTo(B6);

		assertThat(pawn.getPosition()).isEqualTo(B6);
	}

	@Test
	void moveTo_When_Fail() {
		Piece pawn = new Pawn(C3);
		assertThatIllegalArgumentException()
			.isThrownBy(() -> pawn.moveTo(C5))
			.withMessage("기물의 이동 범위에 속하지 않습니다.");
	}
}