package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.position.Position;

class KingTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void King_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		assertThat(new King(pieceColor, new InitialState())).isInstanceOf(King.class);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void King_PieceColor_GenerateInstance(PieceColor pieceColor) {
		assertThat(new King(pieceColor)).isInstanceOf(King.class);
	}

	@Test
	void canLeap_King_ReturnTrue() {
		assertThat(new King(PieceColor.BLACK).canLeap()).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"a4", "a3", "b3", "c3", "c4", "c5", "b5", "a5"})
	void isMovable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("b4");

		assertThat(new King(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d6", "a2", "a6", "d2", "d4"})
	void isMovable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("b4");

		assertThat(new King(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"a4", "a3", "b3", "c3", "c4", "c5", "b5", "a5"})
	void isCatchable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("b4");

		assertThat(new King(PieceColor.WHITE).canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d6", "a2", "a6", "d2", "d4"})
	void isCatchable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("b4");

		assertThat(new King(PieceColor.WHITE).canCatch(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,K", "WHITE,k"})
	void getName_ReturnName(PieceColor pieceColor, String expected) {
		assertThat(new King(pieceColor).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_KingScore_ReturnScore(PieceColor pieceColor) {
		assertThat(new King(pieceColor).getScore()).isEqualTo(0);
	}

}