package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.color.Color;
import chess.domain.move.Direction;
import chess.domain.position.Position;

class PieceTest {

	@Test
	@DisplayName("체스말을 이름을 갖고 있다")
	void name() {
		// given
		final var position = Position.of(A, EIGHT);
		final var piece = new TestPiece(BLACK, position);
		final var expected = "R";

		// when
		final var actual = piece.name();

		// then
		assertThat(actual).isEqualTo(expected);
	}

	static final class TestPiece extends Piece {

		public TestPiece(final Color color, final Position position) {
			super(color, position);
		}

		@Override
		public String name() {
			return "R";
		}

		@Override
		public boolean movable(final Direction direction) {
			return false;
		}

		@Override
		public boolean movableByCount(final int count) {
			return false;
		}
	}
}
