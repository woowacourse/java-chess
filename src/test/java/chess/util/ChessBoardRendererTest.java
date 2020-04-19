package chess.util;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;

class ChessBoardRendererTest {

	@ParameterizedTest
	@NullSource
	void render_NullChessBoard_ExceptionThrown(final ChessBoard chessBoard) {
		assertThatThrownBy(() -> ChessBoardRenderer.render(chessBoard))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 보드가 null입니다.");
	}

	@Test
	void render_ChessBoard_ReturnRenderedChessBoardByStringList() {
		final ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.create());

		final List<String> expected = Arrays.asList(
			"R N B Q K B N R	8",
			"P P P P P P P P	7",
			". . . . . . . .	6",
			". . . . . . . .	5",
			". . . . . . . .	4",
			". . . . . . . .	3",
			"p p p p p p p p	2",
			"r n b q k b n r	1",
			"",
			"a b c d e f g h");
		assertThat(ChessBoardRenderer.render(chessBoard)).isEqualTo(expected);
	}

}