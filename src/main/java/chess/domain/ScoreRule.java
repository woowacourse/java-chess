package chess.domain;

import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.coordinates.Column;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class ScoreRule {
	public static final int MIN_COLUMN_PAWN_COUNT = 2;
	private static final double PAWN_SCORE_DISCOUNT_FACTOR = 0.5;

	private final Map<Coordinates, Piece> pieces;

	public ScoreRule(Map<Coordinates, Piece> pieces) {
		this.pieces = pieces;
	}

	public GameResult calculateScore() {
		Map<Color, Double> eachScore = sumAllScoreOfTeams();
		discountScore(eachScore);
		return new GameResult(eachScore);
	}

	private Map<Color, Double> sumAllScoreOfTeams() {
		return pieces.values()
				.stream()
				.collect(Collectors.toMap(Piece::getColor, Piece::getScore, Double::sum));
	}

	private void discountScore(Map<Color, Double> eachScore) {
		for (Color color : Color.values()) {
			eachScore.put(color, eachScore.get(color) - discountPawnScore(color));
		}
	}

	private double discountPawnScore(Color color) {
		return findEachColumnPawnCountBy(color)
				.values()
				.stream()
				.filter(pawnScore -> pawnScore >= MIN_COLUMN_PAWN_COUNT)
				.mapToDouble(pawnScore -> pawnScore * PAWN_SCORE_DISCOUNT_FACTOR)
				.sum();
	}

	private Map<Column, Long> findEachColumnPawnCountBy(Color color) {
		return pieces.entrySet()
				.stream()
				.filter(cell -> cell.getValue().isTeamOf(color) && cell.getValue().isPawn())
				.collect(Collectors.groupingBy(cell -> cell.getKey().getColumn(), Collectors.counting()));
	}
}
