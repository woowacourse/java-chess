package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.position.Position;

class KnightTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Knight_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		assertThat(new Knight(pieceColor, new InitialState())).isInstanceOf(Knight.class);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Knight_PieceColor_GenerateInstance(PieceColor pieceColor) {
		assertThat(new Knight(pieceColor)).isInstanceOf(Knight.class);
	}

	@Test
	void canLeap_Knight_ReturnTrue() {
		assertThat(new Knight(PieceColor.WHITE).canLeap()).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b3", "b5", "c2", "e2", "f3", "f5", "c6", "e6"})
	void isMovable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Knight(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d5", "e4", "d3", "c4"})
	void isMovable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Knight(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"b3", "b5", "c2", "e2", "f3", "f5", "c6", "e6"})
	void isCatchable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Knight(PieceColor.WHITE).canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d5", "e4", "d3", "c4"})
	void isCatchable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Knight(PieceColor.WHITE).canCatch(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,N", "WHITE,n"})
	void getName_PieceColor_ReturnName(PieceColor pieceColor, String expected) {
		assertThat(new Knight(pieceColor).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_KnightScore_ReturnScore(PieceColor pieceColor) {
		assertThat(new Knight(pieceColor).getScore()).isEqualTo(2.5);
	}

}