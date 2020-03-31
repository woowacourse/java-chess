package domain.board.fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.board.Board;
import domain.board.Rank;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.position.Position;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */

/*
	.KR.....  8
	P.PB....  7
	.P..Q...  6
	........  5
	.....nq.  4
	.....p.p  3
	.....pp.  2
	....rk..  1
	abcdefgh
*/
public class GameRunningBoard {
	private List<Rank> ranks = new ArrayList<>();

	public Board create() {
		List<Piece> rank1 = Arrays.asList(
			new Rook(Position.of("b1"), Team.WHITE),
			new King(Position.of("f1"), Team.WHITE)
		);

		List<Piece> rank2 = Arrays.asList(
			new Pawn(Position.of("f2"), Team.WHITE),
			new Pawn(Position.of("g2"), Team.WHITE)
		);

		List<Piece> rank3 = Arrays.asList(
			new Pawn(Position.of("f3"), Team.WHITE),
			new Pawn(Position.of("h3"), Team.WHITE)
		);

		List<Piece> rank4 = Arrays.asList(
			new Knight(Position.of("f4"), Team.WHITE),
			new Queen(Position.of("g4"), Team.WHITE)
		);

		List<Piece> rank5 = new ArrayList<>();

		List<Piece> rank6 = Arrays.asList(
			new Pawn(Position.of("b6"), Team.BLACK),
			new Queen(Position.of("e6"), Team.BLACK)
		);

		List<Piece> rank7 = Arrays.asList(
			new Pawn(Position.of("a7"), Team.BLACK),
			new Pawn(Position.of("c7"), Team.BLACK),
			new Bishop(Position.of("d7"), Team.BLACK)
		);

		List<Piece> rank8 = Arrays.asList(
			new King(Position.of("b8"), Team.BLACK),
			new Rook(Position.of("c8"), Team.BLACK)
		);

		ranks.add(new Rank(rank1));
		ranks.add(new Rank(rank2));
		ranks.add(new Rank(rank3));
		ranks.add(new Rank(rank4));
		ranks.add(new Rank(rank5));
		ranks.add(new Rank(rank6));
		ranks.add(new Rank(rank7));
		ranks.add(new Rank(rank8));

		return new Board(ranks);
	}
}
