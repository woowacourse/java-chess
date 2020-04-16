package chess.domain.chessPiece.pieceStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.MovedState;
import chess.domain.position.Position;

class BlackPawnStrategyTest {

	@Test
	void BlackPawn_GenerateInstance() {
		assertThat(new BlackPawnStrategy()).isInstanceOf(BlackPawnStrategy.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"c6", "c5"})
	void canMove_InitialStateAndMovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("c7");
		final int pawnMovableRange = new InitialState().getPawnMovableRange();

		assertThat(new BlackPawnStrategy().canMove(sourcePosition, targetPosition, pawnMovableRange)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"c6"})
	void canMove_MovedStateAndMovableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("c7");
		final int pawnMovableRange = new MovedState().getPawnMovableRange();

		assertThat(new BlackPawnStrategy().canMove(sourcePosition, targetPosition, pawnMovableRange)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void canMove_InitialStateAndNotMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("c3");
		final int pawnMovableRange = new InitialState().getPawnMovableRange();

		assertThat(new BlackPawnStrategy().canMove(sourcePosition, targetPosition, pawnMovableRange)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"c5"})
	void canMove_MovedStateAndNotMovableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("c7");
		final int pawnMovableRange = new MovedState().getPawnMovableRange();

		assertThat(new BlackPawnStrategy().canMove(sourcePosition, targetPosition, pawnMovableRange)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void canCatch_CatchableSourcePositionAndTargetPosition_ReturnTrue(final Position targetPosition) {
		final Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnStrategy().canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"c2", "c1"})
	void canCatch_NonCatchableSourcePositionAndTargetPosition_ReturnFalse(final Position targetPosition) {
		final Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnStrategy().canCatch(sourcePosition, targetPosition)).isFalse();
	}

}