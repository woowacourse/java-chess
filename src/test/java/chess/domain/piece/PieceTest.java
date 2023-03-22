package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.move.Direction;
import chess.domain.team.Team;

class PieceTest {

	@Test
	@DisplayName("체스말을 이름을 갖고 있다")
	void name() {
		// given
		final var piece = new TestPiece(Team.BLACK);
		final var expected = "R";

		// when
		final var actual = piece.name();

		// then
		assertThat(actual).isEqualTo(expected);
	}

	static final class TestPiece extends Piece {

		public TestPiece(final Team team) {
			super(team);
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
