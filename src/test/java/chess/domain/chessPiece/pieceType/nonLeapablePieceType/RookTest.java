package chess.domain.chessPiece.pieceType.nonLeapablePieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

class RookTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Rook_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		assertThat(new Rook(pieceColor, new InitialState())).isInstanceOf(Rook.class);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Rook_PieceColor_GenerateInstance(PieceColor pieceColor) {
		assertThat(new Rook(pieceColor)).isInstanceOf(Rook.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"d1", "d8", "a4", "h4"})
	void isMovable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Rook(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "a7", "g1", "g7"})
	void isMovable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Rook(PieceColor.WHITE).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"d1", "d8", "a4", "h4"})
	void isCatchable_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Rook(PieceColor.WHITE).canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "a7", "g1", "g7"})
	void isCatchable_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("d4");

		assertThat(new Rook(PieceColor.WHITE).canCatch(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,R", "WHITE,r"})
	void getName_PieceColor_ReturnName(PieceColor pieceColor, String expected) {
		assertThat(new Rook(pieceColor).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_RookScore_ReturnScore(PieceColor pieceColor) {
		assertThat(new Rook(pieceColor).getScore()).isEqualTo(5);
	}

}