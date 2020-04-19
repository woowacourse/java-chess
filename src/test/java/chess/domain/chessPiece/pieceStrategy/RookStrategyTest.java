package chess.domain.chessPiece.pieceStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class RookStrategyTest {

	@Test
	void RookStrategy_GenerateInstance() {
		assertThat(new RookStrategy()).isInstanceOf(RookStrategy.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"d1", "d8", "a4", "h4"})
	void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new RookStrategy().canMove(sourcePosition, targetPosition, 1)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "a7", "g1", "g7"})
	void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new RookStrategy().canMove(sourcePosition, targetPosition, 1)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"d1", "d8", "a4", "h4"})
	void canCatch_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new RookStrategy().canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "a7", "g1", "g7"})
	void canCatch_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new RookStrategy().canCatch(sourcePosition, targetPosition)).isFalse();
	}

}