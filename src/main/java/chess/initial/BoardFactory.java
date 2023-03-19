package chess.initial;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.team.Team;

public final class BoardFactory {

	private BoardFactory() {
	}

	public static Map<Position, Piece> create() {
		Map<Position, Piece> board = new HashMap<>();
		fillPieces(board);
		fillEmpty(board);
		return board;
	}

	private static void fillPieces(final Map<Position, Piece> board) {
		board.putAll(BlackFactory.create(new HashMap<>()));
		board.putAll(WhiteFactory.create(new HashMap<>()));
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

	private static void fill(Map<Position, Piece> board, Rank rank, File file) {
		if (board.get(Position.of(file.value(), rank.value())) == null) {
			board.put(Position.of(file.value(), rank.value()), new Empty(Team.NONE));
		}
	}
}
