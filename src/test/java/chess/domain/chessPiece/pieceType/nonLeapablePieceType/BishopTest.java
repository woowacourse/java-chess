package chess.domain.chessPiece.pieceType.nonLeapablePieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

class BishopTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Bishop_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		assertThat(new Bishop(pieceColor, new InitialState())).isInstanceOf(Bishop.class);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Bishop_PieceColor_GenerateInstance(PieceColor pieceColor) {
		assertThat(new Bishop(pieceColor)).isInstanceOf(Bishop.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "a7", "h8", "g1"})
	void isMovable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Bishop(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d1", "d8", "h4", "a4"})
	void isMovable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Bishop(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "a7", "h8", "g1"})
	void isCatchable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Bishop(PieceColor.WHITE).canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"d1", "d8", "h4", "a4"})
	void isCatchable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Bishop(PieceColor.WHITE).canCatch(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,B", "WHITE,b"})
	void getName_PieceColor_ReturnName(PieceColor pieceColor, String expected) {
		assertThat(new Bishop(pieceColor).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_BishopScore_ReturnScore(PieceColor pieceColor) {
		assertThat(new Bishop(pieceColor).getScore()).isEqualTo(3);
	}

}