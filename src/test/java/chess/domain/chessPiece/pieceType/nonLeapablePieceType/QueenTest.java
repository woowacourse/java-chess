package chess.domain.chessPiece.pieceType.nonLeapablePieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

class QueenTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Queen_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		assertThat(new Queen(pieceColor)).isInstanceOf(Queen.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "d1", "d8", "a7", "h8", "g1", "a4", "h4"})
	void isMovable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Queen(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b3", "c2", "f3", "e6"})
	void isMovable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Queen(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "d1", "d8", "a7", "h8", "g1", "a4", "h4"})
	void isCatchable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Queen(PieceColor.WHITE).canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b3", "c2", "f3", "e6"})
	void isCatchable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Queen(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,Q", "WHITE,q"})
	void getName_PieceColor_ReturnName(PieceColor pieceColor, String expected) {
		assertThat(new Queen(pieceColor).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_QueenScore_ReturnScore(PieceColor pieceColor) {
		assertThat(new Queen(pieceColor).getScore()).isEqualTo(9);
	}

}