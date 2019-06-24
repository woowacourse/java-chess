package chess.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreCalculator {
	private static final ScoreCalculator INSTANCE = new ScoreCalculator();
	private static final int BOUND_OF_PAWNS = 2;
	private static final double SCORE_OF_PAWN_IF_SAME_COLUMN = 0.5;

	private ScoreCalculator() {
	}

	public static ScoreCalculator getInstance() {
		return INSTANCE;
	}

	public double scoreOfColor(final List<Square> squares, final Piece.Color color) {
		List<Square> squaresFilteredByColor = squares.stream()
				.filter(square -> square.isSameColor(color))
				.collect(Collectors.toList());

		return calculateSum(squaresFilteredByColor);
	}

	private double calculateSum(final List<Square> squares) {
		double sum = 0;

		List<Square> pawns = filterPawns(squares);
		sum += sumOfPawns(pawns);

		List<Square> others = filterExceptPawn(squares, pawns);
		for (final Square square : others) {
			sum += square.getScore();
		}

		return sum;
	}

	private List<Square> filterPawns(final List<Square> squares) {
		List<Square> pawns = squares.stream()
				.filter(square -> square.isPawn())
				.collect(Collectors.toList());
		return pawns;
	}

	private double sumOfPawns(final List<Square> pawns) {
		double sum = 0;
		for (int i = Column.MIN; i <= Column.MAX; i++) {
			sum += sumOfPawnByColumn(pawns, i);
		}
		return sum;
	}

	private double sumOfPawnByColumn(final List<Square> pawns, final int column) {
		List<Square> retPawns = pawns.stream()
				.filter(square -> square.isSameColumn(Column.from(String.valueOf((char) column))))
				.collect(Collectors.toList());
		if (retPawns.size() >= BOUND_OF_PAWNS) {
			return retPawns.size() * SCORE_OF_PAWN_IF_SAME_COLUMN;
		}
		return retPawns.size();
	}

	private List<Square> filterExceptPawn(final List<Square> squares, final List<Square> pawns) {
		List<Square> others = squares.stream()
				.filter(square -> !pawns.contains(square))
				.filter(square -> !square.isEmpty())
				.collect(Collectors.toList());
		return others;
	}
}
