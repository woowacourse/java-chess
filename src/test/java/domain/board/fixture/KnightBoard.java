package domain.board.fixture;

import java.util.ArrayList;
import java.util.List;

import domain.board.Board;
import domain.board.Rank;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.position.Position;
import domain.piece.team.Team;

/*
 * E는 적, N는 아군 Knight을 나타냄
 *
 * 8  . . . . . . . .
 * 7  . . . . . . . .
 * 6  . . . . . . . .
 * 5  . . . . . . . .
 * 4  . . . . . . . .
 * 3  n . E . . . . .
 * 2  . . . . . . . .
 * 1  . n . . . . . .
 *    A B C D E F G H
 * */
public class KnightBoard {
	private List<Rank> ranks = new ArrayList<>();

	public Board create() {
		List<Piece> rank1 = new ArrayList<>();
		makePiece(rank1, "b1", Team.WHITE);

		List<Piece> rank2 = new ArrayList<>();

		List<Piece> rank3 = new ArrayList<>();
		makePiece(rank3, "a3", Team.WHITE);
		makePiece(rank3, "c3", Team.BLACK);

		ranks.add(new Rank(rank1));
		ranks.add(new Rank(rank2));
		ranks.add(new Rank(rank3));
		createEmptyRank(5);
		return new Board(ranks);
	}

	private void makePiece(List<Piece> rank, String position, Team team) {
		rank.add(new Knight(Position.of(position), team));
	}

	private void createEmptyRank(int emptyRankSize) {
		for (int i = 0; i < emptyRankSize; i++) {
			ranks.add(new Rank(new ArrayList<>()));
		}
	}
}
