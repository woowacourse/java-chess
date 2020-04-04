package domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
	private static final int MAX_PAWN_NUMBER_TO_GET_HIGH_SCORE = 1;

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
			.filter(piece -> piece.isSamePosition(Position.of(sourcePosition)))
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
		Supplier<Stream<Piece>> pawnsOnSameColumn = () -> ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> (piece.isSameTeam(team)) && (piece.isSameColumn(column)) && (piece instanceof Pawn));

		if (overOnePawnOnSameColumn(pawnsOnSameColumn.get())) {
			return pawnsOnSameColumn.get()
				.mapToDouble(piece -> PAWNS_ON_SAME_COLUMN_SCORE)
				.sum();
		}
		return pawnsOnSameColumn.get()
			.mapToDouble(Piece::getScore)
			.sum();
	}

	private boolean overOnePawnOnSameColumn(Stream<Piece> pawnsOnSameColumn) {
		return pawnsOnSameColumn
			.count() > MAX_PAWN_NUMBER_TO_GET_HIGH_SCORE;
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

	public List<String> showAllPieces() {
		List<String> pieces = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			pieces.addAll(ranks.get(i).getRankPieces());
		}
		return pieces;
	}
}
