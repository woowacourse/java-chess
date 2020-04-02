package chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.MovedState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

class BlackPawnTest {

	@Test
	void BlackPawn_PieceColorAndPieceState_GenerateInstance() {
		assertThat(new BlackPawn(PieceColor.BLACK, new InitialState())).isInstanceOf(BlackPawn.class);
	}

	@Test
	void BlackPawn_PieceState_GenerateInstance() {
		assertThat(new BlackPawn(new InitialState())).isInstanceOf(BlackPawn.class);
	}

	@Test
	void BlackPawn_GenerateInstance() {
		assertThat(new BlackPawn()).isInstanceOf(BlackPawn.class);
	}

	@Test
	void validate_NotSameColor_ExceptionThrown() {
		assertThatThrownBy(() -> new BlackPawn(PieceColor.WHITE, new InitialState()))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효하지 않은 색상입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"c6", "c5"})
	void isMovable_InitialStateAndMovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("c7");

		assertThat(new BlackPawn().canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"c6"})
	void isMovable_MovedStateAndMovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("c7");

		assertThat(new BlackPawn(new MovedState()).canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void isMovable_InitialStateAndNotMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawn().canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"c5"})
	void isMovable_MovedStateAndNotMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c7");

		assertThat(new BlackPawn(new MovedState()).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void isCatchable_CatchableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawn().canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"c2", "c1"})
	void isCatchable_NonCatchableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawn().canCatch(sourcePosition, targetPosition)).isFalse();
	}

	@Test
	void getName_BlackPawn_UpperCase() {
		assertThat(new BlackPawn().getName()).isEqualTo(BlackPawn.NAME.toUpperCase());
	}

	@Test
	void getScore_BlackPawnScore_ReturnOne() {
		assertThat(new BlackPawn().getScore()).isEqualTo(1);
	}

}