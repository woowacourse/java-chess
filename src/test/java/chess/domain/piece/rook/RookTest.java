package chess.domain.piece.rook;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;

class RookTest {

	@Test
	void moveTo_When_Success() {
		Piece rook = new Rook(C3);
		rook.moveTo(H3);

		assertThat(rook.getPosition()).isEqualTo(H3);
	}

	@Test
	void moveTo_When_Fail() {
		Piece rook = new Rook(C3);
		assertThatIllegalArgumentException()
			.isThrownBy(() -> rook.moveTo(D4))
			.withMessage("기물의 이동 범위에 속하지 않습니다.");
	}
}