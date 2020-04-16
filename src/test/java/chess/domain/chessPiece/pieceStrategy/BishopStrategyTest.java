package chess.domain.chessPiece.pieceStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class BishopStrategyTest {

	@Test
	void BishopStrategy_GenerateInstance() {
		assertThat(new BishopStrategy()).isInstanceOf(BishopStrategy.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "a7", "h8", "g1"})
	void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new BishopStrategy().canMove(sourcePosition, targetPosition, 1)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d1", "d8", "h4", "a4"})
	void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new BishopStrategy().canMove(sourcePosition, targetPosition, 1)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "a7", "h8", "g1"})
	void canCatch_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new BishopStrategy().canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d1", "d8", "h4", "a4"})
	void canCatch_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new BishopStrategy().canCatch(sourcePosition, targetPosition)).isFalse();
	}

}