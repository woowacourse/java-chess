package domain.board;

import java.util.ArrayList;
import java.util.List;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.position.Column;
import domain.piece.position.Position;
import domain.piece.position.Row;
import domain.piece.team.Team;

public class BoardFactory {
	private static final Row WHITE_OTHER_PIECE_RANK = Row.ONE;
	private static final Row WHITE_PAWN_RANK = Row.TWO;
	private static final Row BLACK_PAWN_RANK = Row.SEVEN;
	private static final Row BLACK_OTHER_PIECE_RANK = Row.EIGHT;
	private static final int EMPTY_RANK_SIZE = 4;
	private static final List<Rank> ranks = new ArrayList<>();

	static {
		createRank(WHITE_OTHER_PIECE_RANK, Team.WHITE);
		createPawnRank(WHITE_PAWN_RANK, Team.WHITE);
		createEmptyRank();
		createPawnRank(BLACK_PAWN_RANK, Team.BLACK);
		createRank(BLACK_OTHER_PIECE_RANK, Team.BLACK);
	}

	private BoardFactory() {
	}

	public static Board create() {
		return new Board(new ArrayList<>(ranks));
	}

	private static void createEmptyRank() {
		for (int i = 0; i < EMPTY_RANK_SIZE; i++) {
			ranks.add(new Rank(new ArrayList<>()));
		}
	}

	private static void createRank(Row row, Team team) {
		List<Piece> rank = new ArrayList<>();

		rank.add(new Rook(Position.of(Column.A, row), team));
		rank.add(new Knight(Position.of(Column.B, row), team));
		rank.add(new Bishop(Position.of(Column.C, row), team));
		rank.add(new King(Position.of(Column.D, row), team));
		rank.add(new Queen(Position.of(Column.E, row), team));
		rank.add(new Bishop(Position.of(Column.F, row), team));
		rank.add(new Knight(Position.of(Column.G, row), team));
		rank.add(new Rook(Position.of(Column.H, row), team));

		ranks.add(new Rank(rank));
	}

	private static void createPawnRank(Row row, Team team) {
		List<Piece> rank = new ArrayList<>();
		for (Column column : Column.values()) {
			rank.add(new Pawn(Position.of(column, row), team));
		}
		ranks.add(new Rank(rank));
	}
}
