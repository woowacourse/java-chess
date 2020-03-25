package chess.domain.piece.bishop;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;

class BishopTest {


	@Test
	void moveTo_When_Success() {
		Piece bishop = new Bishop(C3);
		bishop.moveTo(H8);

		assertThat(bishop.getPosition()).isEqualTo(H8);
	}

	@Test
	void moveTo_When_Fail() {
		Piece bishop = new Bishop(C3);
		assertThatIllegalArgumentException()
			.isThrownBy(() -> bishop.moveTo(D3))
			.withMessage("기물의 이동 범위에 속하지 않습니다.");
	}
}