package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;

class ChessGameTest {

	@Test
	void from_ChessBoard_GenerateInstance() {
		assertThat(ChessGame.from(new ChessBoard(ChessBoardInitializer.create()))).isInstanceOf(ChessGame.class);
	}

	@ParameterizedTest
	@NullSource
	void from_NullChessBoard_ExceptionThrown(ChessBoard chessBoard) {
		assertThatThrownBy(() -> ChessGame.from(chessBoard))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 보드가 null입니다.");
	}
}