package chess.domain.chessPiece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.MovedState;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

class ChessPieceTest {

	@Test
	void canMove_IsMovable_ShiftNextState() {
		ChessPiece chessPiece = new King(PieceColor.BLACK);
		chessPiece.canMove(Position.of("b2"), Position.of("b3"));

		assertThat(chessPiece).extracting("pieceState").isInstanceOf(MovedState.class);
	}

	@Test
	void canMove_IsNotMovable_ShiftNextState() {
		ChessPiece chessPiece = new King(PieceColor.BLACK);
		chessPiece.canMove(Position.of("b2"), Position.of("b4"));

		assertThat(chessPiece).extracting("pieceState").isInstanceOf(InitialState.class);
	}

	@ParameterizedTest
	@NullSource
	void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
		ChessPiece chessPiece = new King(PieceColor.BLACK);

		assertThatThrownBy(() -> chessPiece.canCatch(sourcePosition, Position.of("b1")))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("소스 위치가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
		ChessPiece chessPiece = new King(PieceColor.BLACK);

		assertThatThrownBy(() -> chessPiece.canCatch(Position.of("b1"), targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 null입니다.");
	}

	@Test
	void isSamePieceColorWith_SamePieceColor_ReturnTrue() {
		ChessPiece chessPiece1 = new King(PieceColor.BLACK);
		ChessPiece chessPiece2 = new King(PieceColor.BLACK);

		assertThat(chessPiece1.isSamePieceColorWith(chessPiece2)).isTrue();
	}

	@Test
	void isSamePieceColorWith_NotSamePieceColor_ReturnFalse() {
		ChessPiece chessPiece1 = new King(PieceColor.BLACK);
		ChessPiece chessPiece2 = new King(PieceColor.WHITE);

		assertThat(chessPiece1.isSamePieceColorWith(chessPiece2)).isFalse();
	}

	@ParameterizedTest
	@NullSource
	void isSamePieceColorWith_NullChessPiece_ExceptionThrown(ChessPiece chessPiece2) {
		ChessPiece chessPiece1 = new King(PieceColor.BLACK);

		assertThatThrownBy(() -> chessPiece1.isSamePieceColorWith(chessPiece2))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 피스가 null입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,true", "WHITE,false"})
	void isSame_PieceColor_ReturnResultOfComparePieceColor(PieceColor pieceColor, boolean expected) {
		ChessPiece chessPiece = new King(PieceColor.BLACK);

		assertThat(chessPiece.isSame(pieceColor)).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void isSame_PieceColor_ReturnResultOfComparePieceColor(PieceColor pieceColor) {
		ChessPiece chessPiece = new King(PieceColor.BLACK);

		assertThatThrownBy(() -> chessPiece.isSame(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 색상이 null입니다.");
	}
}