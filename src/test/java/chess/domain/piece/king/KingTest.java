package chess.domain.piece.king;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

class KingTest {

	@Test
	void moveTo_When_Success() {
		Piece king = new King(Position.of("c3"));
		king.moveTo(Position.of("c4"));

		assertThat(king.getPosition()).isEqualTo(Position.of("c4"));
	}

	@Test
	void moveTo_When_Fail() {
		Piece king = new King(Position.of("c3"));
		assertThatIllegalArgumentException()
			.isThrownBy(() -> king.moveTo(Position.of("c5")))
			.withMessage("기물의 이동 범위에 속하지 않습니다.");
	}
}