package chess.domain;

import static chess.domain.piece.PieceScore.*;

import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;

public class ScoreRule {
	public static final int MIN_COLUMN_PAWN_COUNT = 2;
	private static final double PAWN_SCORE_DISCOUNT_FACTOR = 2.0;

	private final Map<Position, Piece> pieces;

	public ScoreRule(Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public Map<Color, Double> calculateScore() {
		Map<Color, Double> eachScore = pieces.values()
			.stream()
			.collect(Collectors.toMap(Piece::getColor, Piece::getScore, Double::sum));

		for (Color color : Color.values()) {
			eachScore.put(color, eachScore.get(color) - discountPawnScore(color));
		}

		return eachScore;
	}

	private double discountPawnScore(Color color) {
		return findEachColumnPawnCountBy(color)
			.values()
			.stream()
			.filter(x -> x >= MIN_COLUMN_PAWN_COUNT)
			.map(x -> x / PAWN_SCORE_DISCOUNT_FACTOR)
			.count();
	}

	private Map<Column, Long> findEachColumnPawnCountBy(Color color) {
		return pieces.entrySet()
			.stream()
			.filter(x -> PAWN_SCORE.isSameScore(x.getValue().getScore()))
			.filter(x -> x.getValue().isSameColor(color))
			.collect(Collectors.groupingBy(x -> x.getKey().getColumn(), Collectors.counting()));
	}
}
