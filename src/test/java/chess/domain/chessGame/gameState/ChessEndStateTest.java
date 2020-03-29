package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceType.PieceColor;

class ChessEndStateTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void ChessEndState_PieceColor_GenerateInstance(PieceColor pieceColor) {
		ChessEndState chessEndState = new EndState(pieceColor);

		assertThat(chessEndState).isInstanceOf(ChessEndState.class);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void shiftNextTurnState_ChessEndState_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> new EndState(pieceColor).shiftNextTurnState())
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessage("End 상태에서 변경될 수 없습니다.");
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void shiftEndState_ChessEndState_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> new EndState(pieceColor).shiftEndState(true))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessage("End 상태에서 변경될 수 없습니다.");
	}

}