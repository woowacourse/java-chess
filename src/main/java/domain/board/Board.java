package domain.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.piece.King;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Board {
	public static final int MIN_COLUMN_COUNT = 1;
	public static final int MAX_COLUMN_COUNT = 8;
	public static final int MIN_ROW_COUNT = 1;
	public static final int MAX_ROW_COUNT = 8;
	private static final int ROW_INDEX_IN_POSITION = 1;
	private static final int INITIAL_KING_COUNT = 2;

	private List<Rank> ranks;

	public Board(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public void move(Position sourcePosition, Position targetPosition, Team turn) {
		Rank rank = calculateRank(sourcePosition);
		Piece piece = rank.findPiece(sourcePosition);
		piece.canMove(targetPosition, turn, ranks);
		piece.move(targetPosition, ranks);

	}

	private Rank calculateRank(Position position) {
		return ranks.get(position.getRow() - 1);
	}

	public Map<Team, Double> calculateScore() {
		Map<Team, Double> scoreBoard = new HashMap<>();
		scoreBoard.put(Team.WHITE, calculateScoreByTeam(Team.WHITE));
		scoreBoard.put(Team.BLACK, calculateScoreByTeam(Team.BLACK));
		return scoreBoard;
	}

	private double calculateScoreByTeam(Team team) {
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
		return INITIAL_KING_COUNT == (int)ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece instanceof King)
			.count();
	}

	public List<Rank> getRanks() {
		return ranks;
	}
}
