package domain.board;

import java.util.List;

import domain.piece.King;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.position.Column;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Board {
	private static final int ROW_INDEX = 1;
	private static final double PAWNS_ON_SAME_COLUMN_SCORE = 0.5;
	private static final int COUNT_OF_ALL_KING = 2;

	private List<Rank> ranks;

	public Board(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public List<Rank> getRanks() {
		return ranks;
	}

	public void move(String sourcePosition, String inputTargetPosition, Team turn) {
		int rankLine = Integer.parseInt(String.valueOf(sourcePosition.charAt(ROW_INDEX)));
		Rank rank = ranks.get(rankLine - 1);
		Piece piece = findPiece(sourcePosition, rank);
		Position targetPosition = Position.of(inputTargetPosition);
		if (piece.canMove(targetPosition, turn, ranks)) {
			piece.move(targetPosition, ranks);
		}
	}

	public Piece findPiece(String sourcePosition, Rank rank) {
		return rank.getPieces().stream()
			.filter(piece -> piece.getPosition().isSamePosition(Position.of(sourcePosition)))
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_SOURCE_POSITION));
	}

	public double calculateTeamScore(Team team) {
		double totalScore = 0;
		totalScore += calculatePawnScore(team);
		return ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece.isSameTeam(team))
			.filter(piece -> !(piece instanceof Pawn))
			.mapToDouble(Piece::getScore)
			.reduce(totalScore, Double::sum);
	}

	private double calculatePawnScore(Team team) {
		double totalPawnScore = 0;
		for (Column column : Column.values()) {
			totalPawnScore += calculateEachColumnPawn(column, team);
		}
		return totalPawnScore;
	}

	private double calculateEachColumnPawn(Column column, Team team) {
		if (overOnePawnOnSameColumn(column, team)) {
			return ranks.stream()
				.flatMap(rank -> rank.getPieces().stream())
				.filter(piece -> piece.isSameTeam(team))
				.filter(piece -> piece.getPosition().getColumn() == column)
				.filter(piece -> piece instanceof Pawn)
				.mapToDouble(piece -> PAWNS_ON_SAME_COLUMN_SCORE)
				.sum();
		}
		return ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece.isSameTeam(team))
			.filter(piece -> piece.getPosition().getColumn() == column)
			.filter(piece -> piece instanceof Pawn)
			.mapToDouble(Piece::getScore)
			.sum();
	}

	private boolean overOnePawnOnSameColumn(Column column, Team team) {
		return ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece.isSameTeam(team))
			.filter(piece -> piece.getPosition().getColumn() == column)
			.filter(piece -> piece instanceof Pawn)
			.count() > 1;
	}

	public boolean isKingDead() {
		return ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece instanceof King)
			.count() < COUNT_OF_ALL_KING;
	}

	public Team findWinner() {
		if (isWhiteKingAlive()) {
			return Team.WHITE;
		}
		return Team.BLACK;
	}

	private boolean isWhiteKingAlive() {
		return ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece instanceof King)
			.anyMatch(piece -> piece.isSameTeam(Team.WHITE));
	}
}
