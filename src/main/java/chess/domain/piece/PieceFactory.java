package chess.domain.piece;

import java.util.Map;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

public class PieceFactory {

	// TODO : 중복되는 메서드 reverse를 통해 바꾸기
	public static void create(Map<Position, Piece> board) {
		initPawn(board);
		initRook(board);
		initBishop(board);
		initKnight(board);
		initQueen(board);
		initKing(board);
	}

	private static void initKing(Map<Position, Piece> board) {
		initWhiteKing(board);
		initBlackKing(board);
	}

	private static void initBlackKing(Map<Position, Piece> board) {
		board.put(Position.of(File.E, Rank.EIGHT), new King("black", "K"));
	}

	private static void initWhiteKing(Map<Position, Piece> board) {
		board.put(Position.of(File.E, Rank.ONE), new King("white", "k"));
	}

	private static void initQueen(Map<Position, Piece> board) {
		initWhiteQueen(board);
		initBlackQueen(board);
	}

	private static void initBlackQueen(Map<Position, Piece> board) {
		board.put(Position.of(File.D, Rank.EIGHT), new Queen("black", "Q"));
	}

	private static void initWhiteQueen(Map<Position, Piece> board) {
		board.put(Position.of(File.D, Rank.ONE), new Queen("white", "q"));
	}

	private static void initKnight(Map<Position, Piece> board) {
		initWhiteKnight(board);
		initBlackKnight(board);
	}

	private static void initBlackKnight(Map<Position, Piece> board) {
		board.put(Position.of(File.B, Rank.EIGHT), new Knight("black", "N"));
		board.put(Position.of(File.G, Rank.EIGHT), new Knight("black", "N"));
	}

	private static void initWhiteKnight(Map<Position, Piece> board) {
		board.put(Position.of(File.B, Rank.ONE), new Knight("white", "n"));
		board.put(Position.of(File.G, Rank.ONE), new Knight("white", "n"));
	}

	private static void initBishop(Map<Position, Piece> board) {
		initWhiteBishop(board);
		initBlackBishop(board);
	}

	private static void initBlackBishop(Map<Position, Piece> board) {
		board.put(Position.of(File.C, Rank.EIGHT), new Bishop("black", "B"));
		board.put(Position.of(File.F, Rank.EIGHT), new Bishop("black", "B"));
	}

	private static void initWhiteBishop(Map<Position, Piece> board) {
		board.put(Position.of(File.C, Rank.ONE), new Bishop("white", "b"));
		board.put(Position.of(File.F, Rank.ONE), new Bishop("white", "b"));
	}

	private static void initRook(Map<Position, Piece> board) {
		initWhiteRook(board);
		initBlackRook(board);
	}

	private static void initBlackRook(Map<Position, Piece> board) {
		board.put(Position.of(File.A, Rank.EIGHT), new Rook("black", "R"));
		board.put(Position.of(File.H, Rank.EIGHT), new Rook("black", "R"));
	}

	private static void initWhiteRook(Map<Position, Piece> board) {
		board.put(Position.of(File.A, Rank.ONE), new Rook("white", "r"));
		board.put(Position.of(File.H, Rank.ONE), new Rook("white", "r"));
	}

	private static void initPawn(Map<Position, Piece> board) {
		initWhitePawn(board);
		initBlackPawn(board);
	}

	private static void initBlackPawn(Map<Position, Piece> board) {
		for (File file : File.values()) {
			board.put(Position.of(file, Rank.SEVEN), new Pawn("black", "P"));
		}
	}

	private static void initWhitePawn(Map<Position, Piece> board) {
		for (File file : File.values()) {
			board.put(Position.of(file, Rank.TWO), new Pawn("white", "p"));
		}
	}
}
