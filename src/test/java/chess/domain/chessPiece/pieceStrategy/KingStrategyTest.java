package chess.domain.chessPiece.pieceStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class KingStrategyTest {

	@Test
	void KingStrategy_GenerateInstance() {
		assertThat(new KingStrategy()).isInstanceOf(KingStrategy.class);
	}

	@Test
	void canLeap_King_ReturnTrue() {
		assertThat(new KingStrategy().canLeap()).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"a4", "a3", "b3", "c3", "c4", "c5", "b5", "a5"})
	void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("b4");

		assertThat(new KingStrategy().canMove(sourcePosition, targetPosition, 1)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d6", "a2", "a6", "d2", "d4"})
	void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("b4");

		assertThat(new KingStrategy().canMove(sourcePosition, targetPosition, 1)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"a4", "a3", "b3", "c3", "c4", "c5", "b5", "a5"})
	void canCatch_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("b4");

		assertThat(new KingStrategy().canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d6", "a2", "a6", "d2", "d4"})
	void canCatch_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("b4");

		assertThat(new KingStrategy().canCatch(sourcePosition, targetPosition)).isFalse();
	}

}