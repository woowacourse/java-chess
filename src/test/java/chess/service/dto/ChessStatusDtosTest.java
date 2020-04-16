package chess.service.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.ChessStatus;
import chess.domain.chessPiece.pieceType.PieceColor;

class ChessStatusDtosTest {

	@ParameterizedTest
	@NullSource
	void of_NullChessStatus_ExceptionThrown(final ChessStatus chessStatus) {
		assertThatThrownBy(() -> ChessStatusDtos.of(chessStatus))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 상태가 null입니다.");
	}

	@Test
	void of_ChessStatus_GenerateInstance() {
		final ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.create());
		final ChessStatus chessStatus = chessBoard.calculateStatus();

		final List<ChessStatusDto> expected = new ArrayList<>();
		expected.add(ChessStatusDto.of(PieceColor.WHITE, 38.0));
		expected.add(ChessStatusDto.of(PieceColor.BLACK, 38.0));
		assertThat(ChessStatusDtos.of(chessStatus)).isInstanceOf(ChessStatusDtos.class)
			.extracting("chessStatusDtos").asList().containsExactlyInAnyOrderElementsOf(expected);
	}

}