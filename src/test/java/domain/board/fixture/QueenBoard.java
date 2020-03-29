package domain.board.fixture;

import java.util.ArrayList;
import java.util.List;

import domain.board.Board;
import domain.board.Rank;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.position.Position;
import domain.piece.team.Team;

/*
 * E는 적, Q는 아군 Queen을 나타냄
 *
 * 8  . . . . . . . .
 * 7  . . E . . . . .
 * 6  . . . . . . . .
 * 5  . . . . E . . .
 * 4  . . . . . . . .
 * 3  . . E . . . . .
 * 2  . . . Q . . . .
 * 1  . . . . Q . . .
 *    A B C D E F G H
 * */
public class QueenBoard {
	private List<Rank> ranks = new ArrayList<>();

	public Board create() {
		List<Piece> rank1 = new ArrayList<>();
		makePiece(rank1, "e1", Team.WHITE);

		List<Piece> rank2 = new ArrayList<>();
		makePiece(rank2, "d2", Team.WHITE);

		List<Piece> rank3 = new ArrayList<>();
		makePiece(rank3, "c3", Team.BLACK);

		List<Piece> rank4 = new ArrayList<>();

		List<Piece> rank5 = new ArrayList<>();
		makePiece(rank5, "e5", Team.BLACK);

		List<Piece> rank6 = new ArrayList<>();

		List<Piece> rank7 = new ArrayList<>();
		makePiece(rank7, "c7", Team.BLACK);

		ranks.add(new Rank(rank1));
		ranks.add(new Rank(rank2));
		ranks.add(new Rank(rank3));
		ranks.add(new Rank(rank4));
		ranks.add(new Rank(rank5));
		ranks.add(new Rank(rank6));
		ranks.add(new Rank(rank7));
		createEmptyRank(1);

		return new Board(ranks);
	}

	private void makePiece(List<Piece> rank, String position, Team team) {
		rank.add(new Queen(Position.of(position), team));
	}

	private void createEmptyRank(int emptyRankSize) {
		for (int i = 0; i < emptyRankSize; i++) {
			ranks.add(new Rank(new ArrayList<>()));
		}
	}
}
