package chess.domain.move;

import static chess.domain.color.Color.*;
import static chess.domain.move.Direction.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.position.Position;

class DirectionTest {
	@Test
	@DisplayName("target이 이동할 수 없는 위치면 예외가 발생한다")
	void unavailableDirection() {
		// given
		final Position source = Position.of(C, ONE);
		final Position target = Position.of(D, EIGHT);
		final Bishop bishop = new Bishop(WHITE, source);

		// then
		assertThatThrownBy(() -> calculateDirection(bishop, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스말이 이동할 수 없는 위치입니다");
	}

	@Test
	@DisplayName("target이 이동할 수 없는 위치면 해당 방향을 반환한다")
	void availableDirection() {
		// given
		final Position source = Position.of(B, ONE);
		final Position target = Position.of(C, THREE);
		final Knight knight = new Knight(WHITE, source);

		// then
		assertThat(calculateDirection(knight, target)).isEqualTo(KNIGHT_UP_RIGHT);
	}
}
