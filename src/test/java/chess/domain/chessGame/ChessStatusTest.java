package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.Position;

class ChessStatusTest {

	@Test
	void of_ChessBoard_GenerateInstance() {
		assertThat(ChessStatus.of(ChessBoardInitializer.create())).isInstanceOf(ChessStatus.class);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void of_NullChessBoard_ExceptionThrown(Map<Position, ChessPiece> chessBoard) {
		assertThatThrownBy(() -> ChessStatus.of(chessBoard))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 보드가 존재하지 않습니다.");
	}
}