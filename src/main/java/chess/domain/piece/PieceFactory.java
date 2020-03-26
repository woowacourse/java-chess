package chess.domain.piece;

import java.util.Map;

import chess.domain.board.File;
import chess.domain.board.Position;

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
		board.put(Position.of("e8"), new King("black", "K"));
	}

	private static void initWhiteKing(Map<Position, Piece> board) {
		board.put(Position.of("e1"), new King("white", "k"));
	}

	private static void initQueen(Map<Position, Piece> board) {
		initWhiteQueen(board);
		initBlackQueen(board);
	}

	private static void initBlackQueen(Map<Position, Piece> board) {
		board.put(Position.of("d8"), new Queen("black", "Q"));
	}

	private static void initWhiteQueen(Map<Position, Piece> board) {
		board.put(Position.of("d1"), new Queen("white", "q"));
	}

	private static void initKnight(Map<Position, Piece> board) {
		initWhiteKnight(board);
		initBlackKnight(board);
	}

	private static void initBlackKnight(Map<Position, Piece> board) {
		board.put(Position.of("b8"), new Knight("black", "N"));
		board.put(Position.of("g8"), new Knight("black", "N"));
	}

	private static void initWhiteKnight(Map<Position, Piece> board) {
		board.put(Position.of("b1"), new Knight("white", "n"));
		board.put(Position.of("g1"), new Knight("white", "n"));
	}

	private static void initBishop(Map<Position, Piece> board) {
		initWhiteBishop(board);
		initBlackBishop(board);
	}

	private static void initBlackBishop(Map<Position, Piece> board) {
		board.put(Position.of("c8"), new Bishop("black", "B"));
		board.put(Position.of("f8"), new Bishop("black", "B"));
	}

	private static void initWhiteBishop(Map<Position, Piece> board) {
		board.put(Position.of("c1"), new Bishop("white", "b"));
		board.put(Position.of("f1"), new Bishop("white", "b"));
	}

	private static void initRook(Map<Position, Piece> board) {
		initWhiteRook(board);
		initBlackRook(board);
	}

	private static void initBlackRook(Map<Position, Piece> board) {
		board.put(Position.of("a8"), new Rook("black", "R"));
		board.put(Position.of("h8"), new Rook("black", "R"));
	}

	private static void initWhiteRook(Map<Position, Piece> board) {
		board.put(Position.of("a1"), new Rook("white", "r"));
		board.put(Position.of("h1"), new Rook("white", "r"));
	}

	private static void initPawn(Map<Position, Piece> board) {
		initWhitePawn(board);
		initBlackPawn(board);
	}

	private static void initBlackPawn(Map<Position, Piece> board) {
		for (File file : File.values()) {
			board.put(Position.of(file.getFile() + "7"), new Pawn("black", "P"));
		}
	}

	private static void initWhitePawn(Map<Position, Piece> board) {
		for (File file : File.values()) {
			board.put(Position.of(file.getFile() + "2"), new Pawn("white", "p"));
		}
	}
}
