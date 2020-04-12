package domain.board;

import java.util.List;
import java.util.Optional;

import domain.piece.Piece;
import domain.piece.position.Position;

public class Board {
	public static final int MIN_COLUMN_COUNT = 1;
	public static final int MAX_COLUMN_COUNT = 8;
	private static final int INITIAL_KING_COUNT = 2;

	private List<Rank> ranks;

	public Board(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public Optional<Piece> findPiece(Position position) {
		return ranks.stream()
			.map(rank -> rank.findPiece(position))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.findFirst();
	}

	boolean isKingAlive() {
		int kingCount = ranks.stream()
			.map(Rank::countOfKing)
			.reduce(0, Integer::sum);

		return INITIAL_KING_COUNT == kingCount;
	}

	public void remove(int rankIndex, Piece piece) {
		Rank pieceRank = ranks.get(rankIndex);
		pieceRank.remove(piece);
	}

	public void add(int rankIndex, Piece piece) {
		Rank pieceRank = ranks.get(rankIndex);
		pieceRank.add(piece);
	}

	public List<Rank> getRanks() {
		return ranks;
	}
}
