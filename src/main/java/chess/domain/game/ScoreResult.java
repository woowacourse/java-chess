package chess.domain.game;

import chess.domain.board.position.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreResult {
	private final Map<Color, Double> scores;

	public ScoreResult(List<Piece> pieces) {
		scores = new HashMap<>();
		calculateScore(pieces);
	}

	private void calculateScore(List<Piece> pieces) {
		scores.put(Color.WHITE, calculateScoreBy(Color.WHITE, pieces));
		scores.put(Color.BLACK, calculateScoreBy(Color.BLACK, pieces));
	}

	private double calculateScoreBy(Color color, List<Piece> pieces) {
		double scoreByColor = calculateTotalScoreBy(color, pieces);
		List<Piece> pawns = getPawnsBy(color, pieces);
		Map<Row, Integer> pawnCountByRows = new HashMap<>();

		for (Row row : Row.values()) {
			pawnCountByRows.put(row, getPawnCountBy(row, pawns));
		}

		return scoreByColor - (0.5 * getTotalDuplicatedPawns(pawnCountByRows));
	}

	private Integer getTotalDuplicatedPawns(Map<Row, Integer> pawnCountByRows) {
		return pawnCountByRows.values()
				.stream()
				.filter(x -> x > 1)
				.reduce(0, Integer::sum);
	}

	private int getPawnCountBy(Row row, List<Piece> pawns) {
		return (int) pawns.stream()
				.filter(pawn -> row.isSame(pawn.getPosition().getRow()))
				.count();
	}

	private List<Piece> getPawnsBy(Color color, List<Piece> pieces) {
		return pieces.stream()
				.filter(piece -> piece.isSameColor(color))
				.filter(Piece::isPawn)
				.collect(Collectors.toList());
	}

	private double calculateTotalScoreBy(Color color, List<Piece> pieces) {
		return pieces.stream()
				.filter(piece -> piece.isSameColor(color))
				.collect(Collectors.toList())
				.stream()
				.mapToDouble(Piece::getScore)
				.sum();
	}

	private void validate(Color color) {
		if (Objects.isNull(color) || color.isBlank()) {
			throw new IllegalArgumentException("잘못된 입력입니다.");
		}
	}

	public Set<Color> keySet() {
		return Collections.unmodifiableSet(scores.keySet());
	}

	public double getScoreBy(Color color) {
		validate(color);
		return scores.get(color);
	}
}
