package chess.domain;

import java.util.List;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Square;

public final class Status {
	public static final double PAWN_PENALTY_SCORE = 0.5;
	private final Board board;

	public Status(Board board) {
		this.board = board;
	}

	public double calculateScore(Color color) {
		List<Map.Entry<Square, Piece>> survives = board.filterBy(color);
		return getSum(survives);
	}

	private double getSum(List<Map.Entry<Square, Piece>> survives) {
		double sum = survives.stream()
			.map(entry -> entry.getValue())
			.mapToDouble(Piece::getScore)
			.sum();
		return adjustmentSum(sum, survives);
	}

	private double adjustmentSum(double sum, List<Map.Entry<Square, Piece>> survives) {
		for (File file : File.values()) {
			int count = countPawnInSameFile(survives, file);
			sum -= subtractPawnInSameFile(count);
		}
		return sum;
	}

	private int countPawnInSameFile(List<Map.Entry<Square, Piece>> survives, File file) {
		return (int)survives.stream()
			.filter(entry -> checkPawnInFile(entry, file))
			.count();
	}

	private boolean checkPawnInFile(Map.Entry<Square, Piece> entry, File file) {
		Square square = entry.getKey();
		Piece piece = entry.getValue();

		if (square.checkFile(file) && piece.isPawn()) {
			return true;
		}
		return false;
	}

	private double subtractPawnInSameFile(int count) {
		if (count > 1) {
			return PAWN_PENALTY_SCORE * count;
		}
		return 0;
	}
}
