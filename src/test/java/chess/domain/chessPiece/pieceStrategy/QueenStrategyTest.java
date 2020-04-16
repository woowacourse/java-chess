package chess.domain.chessPiece.pieceStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class QueenStrategyTest {

	@Test
	void QueenStrategy_GenerateInstance() {
		assertThat(new QueenStrategy()).isInstanceOf(QueenStrategy.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "d1", "d8", "a7", "h8", "g1", "a4", "h4"})
	void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new QueenStrategy().canMove(sourcePosition, targetPosition, 1)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b3", "c2", "f3", "e6"})
	void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new QueenStrategy().canMove(sourcePosition, targetPosition, 1)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "d1", "d8", "a7", "h8", "g1", "a4", "h4"})
	void canCatch_MovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new QueenStrategy().canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b3", "c2", "f3", "e6"})
	void canCatch_NonMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("d4");

		assertThat(new QueenStrategy().canCatch(sourcePosition, targetPosition)).isFalse();
	}

}