package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceType.PieceColor;

class ChessEndStateTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void ChessEndState_PieceColor_GenerateInstance(final PieceColor pieceColor) {
		final ChessEndState chessEndState = new EndState(pieceColor);

		assertThat(chessEndState).isInstanceOf(ChessEndState.class);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void shiftNextTurnState_ChessEndState_ExceptionThrown(final PieceColor pieceColor) {
		assertThatThrownBy(() -> new EndState(pieceColor).shiftNextTurnState())
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessage("지원하지 않는 기능입니다.");
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void shiftEndState_ChessEndState_ExceptionThrown(final PieceColor pieceColor) {
		assertThatThrownBy(() -> new EndState(pieceColor).shiftEndState(true))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessage("지원하지 않는 기능입니다.");
	}

	@Test
	void isEndState_ChessEndState_ReturnFalse() {
		assertThat(new EndState(PieceColor.BLACK).isEndState()).isTrue();
	}

}