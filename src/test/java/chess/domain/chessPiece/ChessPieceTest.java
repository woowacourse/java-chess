package chess.domain.chessPiece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.MovedState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.PieceType;
import chess.domain.position.Position;

class ChessPieceTest {

	@ParameterizedTest
	@NullSource
	void ChessPiece_NullPieceType_ExceptionThrown(final PieceType pieceType) {
		assertThatThrownBy(() -> new ChessPiece(pieceType, new InitialState()))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 타입이 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void ChessPiece_NullPieceState_ExceptionThrown(final PieceState pieceState) {
		assertThatThrownBy(() -> new ChessPiece(PieceType.BLACK_KING, pieceState))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 상태가 null입니다.");
	}

	@Test
	void ChessPiece_PieceTypeAndPieceState_GenerateInstance() {
		assertThat(new ChessPiece(PieceType.BLACK_KING, new InitialState())).isInstanceOf(ChessPiece.class);
	}

	@Test
	void ChessPiece_PieceType_GenerateInstance() {
		assertThat(new ChessPiece(PieceType.BLACK_KING)).isInstanceOf(ChessPiece.class);
	}

	@ParameterizedTest
	@NullSource
	void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);

		assertThatThrownBy(() -> chessPiece.canCatch(sourcePosition, Position.of("b1")))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("소스 위치가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);

		assertThatThrownBy(() -> chessPiece.canCatch(Position.of("b1"), targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 null입니다.");
	}

	@Test
	void canMove_CanMovePieceType_ShiftNextState() {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);
		chessPiece.canMove(Position.of("b2"), Position.of("b3"));

		assertThat(chessPiece).extracting("pieceState").isInstanceOf(MovedState.class);
	}

	@Test
	void canMove_CanMovePieceType_ReturnTrue() {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);

		assertThat(chessPiece.canMove(Position.of("b2"), Position.of("b3"))).isTrue();
	}

	@Test
	void canMove_CanNotMovePieceType_InitialState() {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);
		chessPiece.canMove(Position.of("b2"), Position.of("b4"));

		assertThat(chessPiece).extracting("pieceState").isInstanceOf(InitialState.class);
	}

	@Test
	void canMove_CanNotMovePieceType_ReturnFalse() {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);

		assertThat(chessPiece.canMove(Position.of("b2"), Position.of("b4"))).isFalse();
	}

	@Test
	void canCatch_CanCatchPieceType_ShiftNextState() {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);
		chessPiece.canCatch(Position.of("b2"), Position.of("b3"));

		assertThat(chessPiece).extracting("pieceState").isInstanceOf(MovedState.class);
	}

	@Test
	void canCatch_CanCatchPieceType_ReturnTrue() {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);

		assertThat(chessPiece.canCatch(Position.of("b2"), Position.of("b3"))).isTrue();
	}

	@Test
	void canCatch_CanNotCatchPieceType_InitialState() {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);
		chessPiece.canCatch(Position.of("b2"), Position.of("b4"));

		assertThat(chessPiece).extracting("pieceState").isInstanceOf(InitialState.class);
	}

	@Test
	void canCatch_CanNotCatchPieceType_ReturnFalse() {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);

		assertThat(chessPiece.canCatch(Position.of("b2"), Position.of("b4"))).isFalse();
	}

	@ParameterizedTest
	@NullSource
	void isSamePieceColor_NullChessPiece_ExceptionThrown(final ChessPiece chessPiece2) {
		final ChessPiece chessPiece1 = new ChessPiece(PieceType.BLACK_KING);

		assertThatThrownBy(() -> chessPiece1.isSamePieceColor(chessPiece2))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 피스가 null입니다.");
	}

	@Test
	void isSamePieceColor_SamePieceColor_ReturnTrue() {
		final ChessPiece chessPiece1 = new ChessPiece(PieceType.BLACK_KING);
		final ChessPiece chessPiece2 = new ChessPiece(PieceType.BLACK_KING);

		assertThat(chessPiece1.isSamePieceColor(chessPiece2)).isTrue();
	}

	@Test
	void isSamePieceColor_NotSamePieceColor_ReturnFalse() {
		final ChessPiece chessPiece1 = new ChessPiece(PieceType.BLACK_KING);
		final ChessPiece chessPiece2 = new ChessPiece(PieceType.WHITE_KING);

		assertThat(chessPiece1.isSamePieceColor(chessPiece2)).isFalse();
	}

	@ParameterizedTest
	@NullSource
	void isSame_PieceColor_ReturnResultOfComparePieceColor(final PieceColor pieceColor) {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);

		assertThatThrownBy(() -> chessPiece.isSame(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 색상이 null입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,true", "WHITE,false"})
	void isSame_PieceColor_ReturnResultOfComparePieceColor(final PieceColor pieceColor, final boolean expected) {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_KING);

		assertThat(chessPiece.isSame(pieceColor)).isEqualTo(expected);
	}
}