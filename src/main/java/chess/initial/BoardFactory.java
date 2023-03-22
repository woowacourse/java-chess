package chess.initial;

import static chess.domain.team.Team.*;

import java.util.HashMap;
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

	private static final String A1 = "a1";
	private static final String B1 = "b1";
	private static final String C1 = "c1";
	private static final String D1 = "d1";
	private static final String E1 = "e1";

	private BoardFactory() {
	}

	public static Map<Position, Piece> create() {
		Map<Position, Piece> board = new HashMap<>();
		fillPieces(board);
		fillEmpty(board);
		return board;
	}

	private static void fillPieces(final Map<Position, Piece> board) {
		fillFour(board, A1, new Rook(WHITE), new Rook(BLACK));
		fillFour(board, B1, new Knight(WHITE), new Knight(BLACK));
		fillFour(board, C1, new Bishop(WHITE), new Bishop(BLACK));
		fillTwo(board, D1, new Queen(WHITE), new Queen(BLACK));
		fillTwo(board, E1, new King(WHITE), new King(BLACK));
		fillPawn(board);
	}

	private static void fillFour(final Map<Position, Piece> board, final String point, final Piece white,
		final Piece black) {
		fillTwo(board, point, white, black);
		board.put(Position.side(point), white);
		board.put(Position.diagonal(point), black);
	}

	private static void fillTwo(final Map<Position, Piece> board, final String point, final Piece white,
		final Piece black) {
		board.put(Position.from(point), white);
		board.put(Position.oppsite(point), black);
	}

	private static void fillPawn(final Map<Position, Piece> board) {
		for (final File file : File.values()) {
			board.put(Position.of(file, Rank.TWO), new Pawn(WHITE));
			board.put(Position.of(file, Rank.SEVEN), new Pawn(BLACK));
		}
	}

	private static void fillEmpty(final Map<Position, Piece> board) {
		for (final Rank rank : Rank.values()) {
			findEmpty(board, rank);
		}
	}

	private static void findEmpty(final Map<Position, Piece> board, final Rank rank) {
		for (final File file : File.values()) {
			fill(board, rank, file);
		}
	}

	private static void fill(final Map<Position, Piece> board, final Rank rank, final File file) {
		if (board.get(Position.of(file, rank)) == null) {
			board.put(Position.of(file, rank), new Empty(NONE));
		}
	}
}
