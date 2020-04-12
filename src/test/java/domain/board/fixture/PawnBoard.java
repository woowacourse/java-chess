package domain.board.fixture;

import java.util.ArrayList;
import java.util.List;

import domain.board.Board;
import domain.board.Rank;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.position.Position;
import domain.piece.team.Team;

/*
 * E는 적, p는 아군 pawn을 나타냄
 *
 * 8  . . . . . . . .
 * 7  . . . . . . . .
 * 6  . . . . . . . .
 * 5  . E . . . . . .
 * 4  . . . . . . . .
 * 3  p . . . . . E .
 * 2  p . . . E p p .
 * 1  p p . p p p p .
 *    A B C D E F G H
 * */
public class PawnBoard {
	private List<Rank> ranks = new ArrayList<>();

	public Board create() {
		List<Piece> rank1 = new ArrayList<>();
		makePawn(rank1, "a1", Team.WHITE);
		makePawn(rank1, "b1", Team.WHITE);
		makePawn(rank1, "d1", Team.WHITE);
		makePawn(rank1, "e1", Team.WHITE);
		makePawn(rank1, "f1", Team.WHITE);
		makePawn(rank1, "g1", Team.WHITE);

		List<Piece> rank2 = new ArrayList<>();
		makePawn(rank2, "a2", Team.WHITE);
		makePawn(rank2, "e2", Team.BLACK);
		makePawn(rank2, "f2", Team.WHITE);
		makePawn(rank2, "g2", Team.WHITE);

		List<Piece> rank3 = new ArrayList<>();
		makePawn(rank3, "a3", Team.WHITE);
		makePawn(rank3, "g3", Team.BLACK);

		List<Piece> rank4 = new ArrayList<>();
		List<Piece> rank5 = new ArrayList<>();

		makePawn(rank5, "b5", Team.BLACK);

		ranks.add(new Rank(rank1));
		ranks.add(new Rank(rank2));
		ranks.add(new Rank(rank3));
		ranks.add(new Rank(rank4));
		ranks.add(new Rank(rank5));
		createEmptyRank(3);

		return new Board(ranks);
	}

	private void makePawn(List<Piece> rank, String position, Team team) {
		rank.add(new Pawn(Position.of(position), team));
	}

	private void createEmptyRank(int emptyRankSize) {
		for (int i = 0; i < emptyRankSize; i++) {
			ranks.add(new Rank(new ArrayList<>()));
		}
	}
}
