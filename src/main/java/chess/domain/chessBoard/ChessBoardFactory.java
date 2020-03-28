package chess.domain.chessBoard;

import java.util.HashMap;
import java.util.Map;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.RuleStrategy.KnightRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.BishopRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.QueenRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.RookRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.InitialStateChess;
import chess.domain.chessPiece.pieceType.Bishop;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.Knight;
import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.Queen;
import chess.domain.chessPiece.pieceType.Rook;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

// TODO: 2020/03/26 InitialChessBoard로 명명 수정? test는 삭제해도 괜찮을 듯, Factory 패턴에 대해서 찾아보기
public class ChessBoardFactory {

	public static Map<Position, ChessPiece> create() {
		Map<Position, ChessPiece> chessBoard = new HashMap<>();

		addOtherPiecesBy(chessBoard, PieceColor.WHITE, 1);
		addPawnPiecesBy(chessBoard, PieceColor.WHITE, 2);
		addPawnPiecesBy(chessBoard, PieceColor.BLACK, 7);
		addOtherPiecesBy(chessBoard, PieceColor.BLACK, 8);
		return chessBoard;
	}

	private static void addPawnPiecesBy(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor, int rank) {
		for (ChessFile file : ChessFile.values()) {
			chessBoard.put(Position.of(file, ChessRank.from(rank)), new Pawn(pieceColor,
				new InitialStateChess(pieceColor.getPawnRuleStrategyBy(PawnRuleStrategy.INITIAL_STATE_MOVABLE_RANGE))));
		}
	}

	private static void addOtherPiecesBy(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor, int rank) {
		chessBoard.put(Position.of("a" + rank), new Rook(pieceColor, new InitialStateChess(new RookRuleStrategy())));
		chessBoard.put(Position.of("b" + rank),
			new Knight(pieceColor, new InitialStateChess(new KnightRuleStrategy())));
		chessBoard.put(Position.of("c" + rank),
			new Bishop(pieceColor, new InitialStateChess(new BishopRuleStrategy())));
		chessBoard.put(Position.of("d" + rank), new Queen(pieceColor, new InitialStateChess(new QueenRuleStrategy())));
		chessBoard.put(Position.of("e" + rank), new King(pieceColor, new InitialStateChess(new KingRuleStrategy())));
		chessBoard.put(Position.of("f" + rank),
			new Bishop(pieceColor, new InitialStateChess(new BishopRuleStrategy())));
		chessBoard.put(Position.of("g" + rank),
			new Knight(pieceColor, new InitialStateChess(new KnightRuleStrategy())));
		chessBoard.put(Position.of("h" + rank), new Rook(pieceColor, new InitialStateChess(new RookRuleStrategy())));
	}

}
