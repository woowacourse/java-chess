package chess.service.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceType;
import chess.domain.position.Position;

class ChessBoardDtoTest {

	private static final String IMAGE_SOURCE_FORMAT = "<img class=\"chessboard\" src=\"./images/%s.png\">";

	@ParameterizedTest
	@NullSource
	void of_NullChessBoard_ExceptionThrown(final ChessBoard chessBoard) {
		assertThatThrownBy(() -> ChessBoardDto.of(chessBoard))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 보드가 null입니다.");
	}

	@Test
	void of_ChessBoard_GenerateInstance() {
		final Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		final Position rookPosition = Position.of("b1");
		final Position blackPawnPosition = Position.of("b3");
		initialChessBoard.put(rookPosition, new ChessPiece(PieceType.BLACK_ROOK));
		initialChessBoard.put(blackPawnPosition, new ChessPiece(PieceType.BLACK_PAWN));
		final ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		final Map<String, String> expected = new HashMap<>();
		expected.put(rookPosition.key(), String.format(IMAGE_SOURCE_FORMAT, "black-rook"));
		expected.put(blackPawnPosition.key(), String.format(IMAGE_SOURCE_FORMAT, "black-pawn"));
		assertThat(ChessBoardDto.of(chessBoard)).isInstanceOf(ChessBoardDto.class)
			.extracting("chessBoard").isEqualTo(expected);
	}

}