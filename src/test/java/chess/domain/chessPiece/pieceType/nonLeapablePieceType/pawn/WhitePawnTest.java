package chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.MovedState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

class WhitePawnTest {

	@Test
	void WhitePawn_PieceColorAndPieceState_GenerateInstance() {
		assertThat(new WhitePawn(PieceColor.WHITE, new InitialState())).isInstanceOf(WhitePawn.class);
	}

	@Test
	void WhitePawn_PieceState_GenerateInstance() {
		assertThat(new WhitePawn(new InitialState())).isInstanceOf(WhitePawn.class);
	}

	@Test
	void WhitePawn_GenerateInstance() {
		assertThat(new WhitePawn()).isInstanceOf(WhitePawn.class);
	}

	@Test
	void validate_NotSameColor_ExceptionThrown() {
		assertThatThrownBy(() -> new WhitePawn(PieceColor.BLACK, new InitialState()))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효하지 않은 색상입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"c4", "c5"})
	void isMovable_InitialStateAndMovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new WhitePawn().canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"c8"})
	void isMovable_MovedStateAndMovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("c7");

		assertThat(new WhitePawn(new MovedState()).canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void isMovable_InitialStateAndNotMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c1");

		assertThat(new WhitePawn().canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"c5"})
	void isMovable_MovedStateAndNotMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new WhitePawn(new MovedState()).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void isCatchable_CatchableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("c1");

		assertThat(new WhitePawn().canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"c2", "c3"})
	void isCatchable_NonCatchableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c1");

		assertThat(new WhitePawn().canCatch(sourcePosition, targetPosition)).isFalse();
	}

	@Test
	void getName_WhitePawn_LowerCase() {
		assertThat(new WhitePawn().getName()).isEqualTo(WhitePawn.NAME.toLowerCase());
	}

	@Test
	void getScore_WhitePawnScore_ReturnOne() {
		assertThat(new WhitePawn().getScore()).isEqualTo(1);
	}

}