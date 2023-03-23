package chess.initial;

import static chess.domain.color.Color.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public final class BoardFactory {

	private BoardFactory() {
	}

	public static Map<Position, Piece> create() {
		Map<Position, Piece> board = new HashMap<>();
		addRook(board);
		addBishop(board);
		addKnight(board);
		addQueen(board);
		addKing(board);
		addPawn(board);
		addEmpty(board);
		return board;
	}

	private static void addRook(final Map<Position, Piece> board) {
		List<Position> rookPosition = Rook.getInitialBlackPosition();
		for (Position position : rookPosition) {
			board.put(position, new Rook(BLACK, position));
		}
		rookPosition = Rook.getInitialWhitePosition();
		for (Position position : rookPosition) {
			board.put(position, new Rook(WHITE, position));
		}
	}

	private static void addKnight(final Map<Position, Piece> board) {
		List<Position> knightPosition = Knight.getInitialBlackPosition();
		for (Position position : knightPosition) {
			board.put(position, new Knight(BLACK, position));
		}
		knightPosition = Knight.getInitialWhitePosition();
		for (Position position : knightPosition) {
			board.put(position, new Knight(WHITE, position));
		}
	}

	private static void addBishop(final Map<Position, Piece> board) {
		List<Position> bishopPosition = Bishop.getInitialBlackPosition();
		for (Position position : bishopPosition) {
			board.put(position, new Bishop(BLACK, position));
		}
		bishopPosition = Bishop.getInitialWhitePosition();
		for (Position position : bishopPosition) {
			board.put(position, new Bishop(WHITE, position));
		}
	}

	private static void addQueen(final Map<Position, Piece> board) {
		List<Position> queenPosition = Queen.getInitialBlackPosition();
		for (Position position : queenPosition) {
			board.put(position, new Queen(BLACK, position));
		}
		queenPosition = Queen.getInitialWhitePosition();
		for (Position position : queenPosition) {
			board.put(position, new Queen(WHITE, position));
		}
	}

	private static void addKing(final Map<Position, Piece> board) {
		List<Position> kingPosition = King.getInitialBlackPosition();
		for (Position position : kingPosition) {
			board.put(position, new King(BLACK, position));
		}
		kingPosition = King.getInitialWhitePosition();
		for (Position position : kingPosition) {
			board.put(position, new King(WHITE, position));
		}
	}

	private static void addPawn(final Map<Position, Piece> board) {
		List<Position> pawnPosition = Pawn.getInitialBlackPosition();
		for (Position position : pawnPosition) {
			board.put(position, new Pawn(BLACK, position));
		}
		pawnPosition = Pawn.getInitialWhitePosition();
		for (Position position : pawnPosition) {
			board.put(position, new Pawn(WHITE, position));
		}
	}

	private static void addEmpty(final Map<Position, Piece> board) {
		for (Rank rank : Rank.getRanks()) {
			findEmpty(board, rank);
		}
	}

	private static void findEmpty(final Map<Position, Piece> board, final Rank rank) {
		for (File file : File.getFiles()) {
			addWhenNone(board, rank, file);
		}
	}

	private static void addWhenNone(final Map<Position, Piece> board, final Rank rank, final File file) {
		final Position position = Position.of(file, rank);
		if (!board.containsKey(position)) {
			board.put(position, new Empty(NONE, position));
		}
	}
}
