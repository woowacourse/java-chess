package chess.service.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.ChessGame;

class ChessGameDtoTest {

	@ParameterizedTest
	@NullSource
	void of_NullChessGame_ExceptionThrown(final ChessGame chessGame) {
		assertThatThrownBy(() -> ChessGameDto.of(chessGame))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 게임이 null입니다.");
	}

	@Test
	void of_ChessGame_GenerateInstance() {
		final ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.create());
		final ChessGame chessGame = ChessGame.from(chessBoard);

		assertThat(ChessGameDto.of(chessGame)).isInstanceOf(ChessGameDto.class);
	}

}