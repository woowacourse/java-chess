package domain.board;

import java.util.ArrayList;
import java.util.List;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.pawn.Pawn;
import domain.piece.position.Column;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class BoardFixture {
	private static final String WHITE_OTHER_PIECE_RANK = "1";
	private static final String WHITE_PAWN_RANK = "2";
	private static final String BLACK_PAWN_RANK = "3";
	private static final String BLACK_OTHER_PIECE_RANK = "8";
	private static final int EMPTY_RANK_SIZE = 4;
	private static List<Rank> ranks = new ArrayList<>();

	public static Board create() {
		createRank(WHITE_OTHER_PIECE_RANK, Team.WHITE);
		createPawnRank(WHITE_PAWN_RANK, Team.WHITE);
		createEmptyRank();
		createPawnRank(BLACK_PAWN_RANK, Team.BLACK);
		createRank(BLACK_OTHER_PIECE_RANK, Team.BLACK);
		return new Board(ranks);
	}

	private static void createEmptyRank() {
		for (int i = 0; i < EMPTY_RANK_SIZE; i++) {
			ranks.add(new Rank(new ArrayList<>()));
		}
	}

	private static void createRank(String row, Team team) {
		List<Piece> rank = new ArrayList<>();

		rank.add(new Rook(Position.of("a" + row), team));
		rank.add(new Knight(Position.of("b" + row), team));
		rank.add(new Bishop(Position.of("c" + row), team));
		rank.add(new King(Position.of("d" + row), team));
		rank.add(new Queen(Position.of("e" + row), team));
		rank.add(new Bishop(Position.of("f" + row), team));
		rank.add(new Knight(Position.of("g" + row), team));
		rank.add(new Rook(Position.of("h" + row), team));

		ranks.add(new Rank(rank));
	}

	private static void createPawnRank(String row, Team team) {
		List<Piece> rank = new ArrayList<>();
		for (Column column : Column.values()) {
			rank.add(new Pawn(Position.of(column.getColumnName() + row), team));
		}
		ranks.add(new Rank(rank));
	}
}
