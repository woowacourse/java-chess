package chess.domain.chessPiece.pieceStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class KnightStrategyTest {

	@Test
	void KnightStrategy_GenerateInstance() {
		assertThat(new KnightStrategy()).isInstanceOf(KnightStrategy.class);
	}

	@Test
	void canLeap_Knight_ReturnTrue() {
		assertThat(new KnightStrategy().canLeap()).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b3", "b5", "c2", "e2", "f3", "f5", "c6", "e6"})
	void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new KnightStrategy().canMove(sourcePosition, targetPosition, 1)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d5", "e4", "d3", "c4"})
	void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new KnightStrategy().canMove(sourcePosition, targetPosition, 1)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"b3", "b5", "c2", "e2", "f3", "f5", "c6", "e6"})
	void canCatch_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new KnightStrategy().canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d5", "e4", "d3", "c4"})
	void canCatch_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new KnightStrategy().canCatch(sourcePosition, targetPosition)).isFalse();
	}

}