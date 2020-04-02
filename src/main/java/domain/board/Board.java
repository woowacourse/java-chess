package domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.position.Position;
import domain.piece.team.Team;

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

	double calculateScoreByTeam(Team team) {
		double sum = ranks.stream()
			.map(Rank::calculateScore)
			.reduce(0.0, Double::sum);

		return applyPawnScore(team, sum);
	}

	private double applyPawnScore(Team team, double sum) {
		List<Piece> pawn = new ArrayList<>();

		ranks.stream()
			.map(rank -> rank.findPawn(team))
			.map(pawn::addAll)
			.close();

		if (isSameColumn(pawn)) {
			sum += pawn.size() * Pawn.PAWN_SCORE_WHEN_HAS_SAME_COLUMN;
		}
		return sum;
	}

	private boolean isSameColumn(List<Piece> pawns) {
		int distinctCount = (int)pawns.stream()
			.map(pawn -> pawn.getPosition().getColumn())
			.distinct()
			.count();

		return pawns.size() != distinctCount;
	}

	boolean isKingAlive() {
		int kingCount = ranks.stream()
			.map(Rank::calculateCountOfKing)
			.reduce(0, Integer::sum);

		return INITIAL_KING_COUNT == kingCount;
	}

	private Rank calculateRank(Position position) {
		return ranks.get(position.getRow().getRankIndex());
	}

	public void remove(Piece piece) {
		calculateRank(piece.getPosition()).getPieces().remove(piece);
	}

	public void add(Piece piece, Position targetPosition) {
		calculateRank(targetPosition).getPieces().add(piece);
	}

	public List<Rank> getRanks() {
		return ranks;
	}
}
