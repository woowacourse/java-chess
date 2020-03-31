package chess.domain;

import static chess.domain.piece.BlackPawn.*;

import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Column;

public class ScoreRule {
	private static final int MIN_COLUMN_PAWN_COUNT = 2;
	private static final double PAWN_SCORE_DISCOUNT_FACTOR = 0.5;

	public Map<Color, Double> calculateScore(Board board) {
		Map<Color, Double> eachScore = board.getPieces()
			.values()
			.stream()
			.collect(Collectors.toMap(Piece::getColor, Piece::getScore, Double::sum));

		for (Color color : Color.values()) {
			eachScore.put(color, eachScore.get(color) - discountPawnScore(color, board));
		}

		return eachScore;
	}

	private double discountPawnScore(Color color, Board board) {
		return findEachColumnPawnCountBy(color, board)
			.values()
			.stream()
			.filter(x -> x >= MIN_COLUMN_PAWN_COUNT)
			.map(x -> x * PAWN_SCORE_DISCOUNT_FACTOR)
			.count();
	}

	private Map<Column, Long> findEachColumnPawnCountBy(Color color, Board board) {
		return board.getPieces()
			.entrySet()
			.stream()
			.filter(x -> x.getValue().getScore() == PAWN_SCORE)
			.filter(x -> x.getValue().isSameColor(color))
			.collect(Collectors.groupingBy(x -> x.getKey().getColumn(), Collectors.counting()));
	}
}
