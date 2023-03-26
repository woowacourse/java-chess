package chess.domain.move;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.color.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

class DirectionTest {
	@Test
	@DisplayName("target이 이동할 수 없는 위치면 예외가 발생한다")
	void unavailableDirection() {
		// given
		final Position source = Position.of(File.C, Rank.ONE);
		final Position target = Position.of(File.D, Rank.ONE);
		final Bishop bishop = new Bishop(Color.WHITE, source);

		// then
		assertThatThrownBy(() -> Direction.calculateDirection(source, target, bishop))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스말이 이동할 수 없는 위치입니다");
	}

	@Test
	@DisplayName("target이 이동할 수 없는 위치면 해당 방향을 반환한다")
	void availableDirection() {
		// given
		final Position source = Position.of(File.B, Rank.ONE);
		final Position target = Position.of(File.C, Rank.THREE);
		final Knight knight = new Knight(Color.WHITE, source);

		// when
		final Direction direction = Direction.KNIGHT_UP_RIGHT;

		// then
		assertThat(Direction.calculateDirection(source, target, knight)).isEqualTo(direction);
	}
}
