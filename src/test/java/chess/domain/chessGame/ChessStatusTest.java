package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.PieceType;
import chess.domain.position.Position;

class ChessStatusTest {

	@ParameterizedTest
	@NullAndEmptySource
	void of_NullChessBoard_ExceptionThrown(final Map<Position, ChessPiece> chessBoard) {
		assertThatThrownBy(() -> ChessStatus.of(chessBoard))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 보드가 존재하지 않습니다.");
	}

	@Test
	void of_ChessBoard_GenerateInstance() {
		assertThat(ChessStatus.of(ChessBoardInitializer.create())).isInstanceOf(ChessStatus.class);
	}

	@Test
	void of_PawnsOnSameFile_CalculateScoreHalf() {
		final Map<Position, ChessPiece> chessBoard = new HashMap<>();
		chessBoard.put(Position.of("b3"), new ChessPiece(PieceType.BLACK_PAWN));
		chessBoard.put(Position.of("b4"), new ChessPiece(PieceType.BLACK_PAWN));

		assertThat(ChessStatus.of(chessBoard).getStatusOf(PieceColor.BLACK)).isEqualTo(1);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getStatusOf_ChessBoard_CalculateEachPieceColorStatus(final PieceColor pieceColor) {
		final Map<Position, ChessPiece> chessBoard = new HashMap<>();
		chessBoard.put(Position.of("b1"), new ChessPiece(PieceType.BLACK_KING));
		chessBoard.put(Position.of("b2"), new ChessPiece(PieceType.BLACK_QUEEN));
		chessBoard.put(Position.of("b3"), new ChessPiece(PieceType.BLACK_ROOK));
		chessBoard.put(Position.of("a1"), new ChessPiece(PieceType.WHITE_KING));
		chessBoard.put(Position.of("a2"), new ChessPiece(PieceType.WHITE_QUEEN));
		chessBoard.put(Position.of("a3"), new ChessPiece(PieceType.WHITE_ROOK));

		assertThat(ChessStatus.of(chessBoard).getStatusOf(pieceColor)).isEqualTo(14);
	}

}