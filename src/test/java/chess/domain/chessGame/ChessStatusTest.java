package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.QueenRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.RookRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.BlackPawnRuleStrategy;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.Queen;
import chess.domain.chessPiece.pieceType.Rook;
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
		chessBoard.put(Position.of("b1"), new King(pieceColor, new InitialState(new KingRuleStrategy())));
		chessBoard.put(Position.of("b2"), new Queen(pieceColor, new InitialState(new QueenRuleStrategy())));
		chessBoard.put(Position.of("b3"), new Rook(pieceColor, new InitialState(new RookRuleStrategy())));

		assertThat(ChessStatus.of(chessBoard).getStatusOf(pieceColor)).isEqualTo(14);
	}

	@Test
	void calculatePawnScore_PawnsOnSameFile_CalculateScoreHalf() {
		Map<Position, ChessPiece> chessBoard = new HashMap<>();
		chessBoard.put(Position.of("b3"), new Pawn(PieceColor.BLACK, new InitialState(new BlackPawnRuleStrategy(2))));
		chessBoard.put(Position.of("b4"), new Pawn(PieceColor.BLACK, new InitialState(new BlackPawnRuleStrategy(2))));

		assertThat(ChessStatus.of(chessBoard).getStatusOf(PieceColor.BLACK)).isEqualTo(1);
	}

}