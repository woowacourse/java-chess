package chess.web.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.Rook;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn.BlackPawn;
import chess.domain.position.Position;

class ChessBoardDtoTest {

	@Test
	void of_ChessBoard_GenerateInstance() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("b1"), new Rook(PieceColor.BLACK));
		initialChessBoard.put(Position.of("b3"), new BlackPawn());

		Map<String, String> expected = new HashMap<>();
		expected.put("b1", "<img class=\"chessboard\" src=\"./images/black-rook.png\">");
		expected.put("b3", "<img class=\"chessboard\" src=\"./images/black-pawn.png\">");
		assertThat(ChessBoardDto.of(initialChessBoard)).isInstanceOf(ChessBoardDto.class)
			.extracting("chessBoardDto").isEqualTo(expected);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validate_InvalidChessBoard_ExceptionThrown(Map<Position, ChessPiece> chessBoard) {
		assertThatThrownBy(() -> ChessBoardDto.of(chessBoard))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 보드가 존재하지 않습니다.");
	}

}