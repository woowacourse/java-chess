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
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.Queen;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.Rook;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn.BlackPawn;
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

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void calculateStatusOf_ChessBoard_CalculateEachPieceColorStatus(PieceColor pieceColor) {
		Map<Position, ChessPiece> chessBoard = new HashMap<>();
		chessBoard.put(Position.of("b1"), new King(pieceColor));
		chessBoard.put(Position.of("b2"), new Queen(pieceColor));
		chessBoard.put(Position.of("b3"), new Rook(pieceColor));

		assertThat(ChessStatus.of(chessBoard).getStatusOf(pieceColor)).isEqualTo(14);
	}

	@Test
	void calculatePawnScore_PawnsOnSameFile_CalculateScoreHalf() {
		Map<Position, ChessPiece> chessBoard = new HashMap<>();
		chessBoard.put(Position.of("b3"), new BlackPawn());
		chessBoard.put(Position.of("b4"), new BlackPawn());

		assertThat(ChessStatus.of(chessBoard).getStatusOf(PieceColor.BLACK)).isEqualTo(1);
	}

}