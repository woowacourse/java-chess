package domain.board;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.piece.King;
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

	public Rank calculateRank(Position position) {
		return ranks.get(position.getRow().getRankIndex());
	}

	public double calculateScoreByTeam(Team team) {
		List<Piece> pawn = ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece instanceof Pawn)
			.filter(piece -> piece.isTeam(team))
			.collect(Collectors.toList());

		double sum = ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece.isTeam(team))
			.mapToDouble(Piece::getScore)
			.sum();

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

	public boolean isKingAlive() {
		int kingCount = (int)ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece instanceof King)
			.count();
		return INITIAL_KING_COUNT == kingCount;
	}

	public Optional<Piece> findPiece(Position position) {
		return ranks.stream()
			.map(rank -> rank.findPiece(position))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.findFirst();
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
