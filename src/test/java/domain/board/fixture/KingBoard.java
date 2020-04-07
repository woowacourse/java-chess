package domain.board.fixture;

import java.util.ArrayList;
import java.util.List;

import domain.board.Board;
import domain.board.Rank;
import domain.piece.King;
import domain.piece.Piece;
import domain.piece.position.Position;
import domain.piece.team.Team;

/*
 * E는 적, K는 아군 King을 나타냄
 *
 * 8  . . . . . . . .
 * 7  . . . . . . . .
 * 6  . . . . . . . .
 * 5  . . . . . . . .
 * 4  . . . . . . . .
 * 3  . . . . . . . .
 * 2  . E k . . . . .
 * 1  . k . . . . . .
 *    A B C D E F G H
 * */
public class KingBoard {
	private List<Rank> ranks = new ArrayList<>();

	public Board create() {
		List<Piece> rank1 = new ArrayList<>();
		makePiece(rank1, "b1", Team.WHITE);

		List<Piece> rank2 = new ArrayList<>();
		makePiece(rank2, "b2", Team.BLACK);
		makePiece(rank2, "c2", Team.WHITE);

		ranks.add(new Rank(rank1));
		ranks.add(new Rank(rank2));
		createEmptyRank(6);
		return new Board(ranks);
	}

	private void makePiece(List<Piece> rank, String position, Team team) {
		rank.add(new King(Position.of(position), team));
	}

	private void createEmptyRank(int emptyRankSize) {
		for (int i = 0; i < emptyRankSize; i++) {
			ranks.add(new Rank(new ArrayList<>()));
		}
	}
}
