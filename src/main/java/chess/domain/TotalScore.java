package chess.domain;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class TotalScore {
	private final double totalScore;

	public TotalScore(List<Piece> pieces) {
		this.totalScore = calculateTotalScore(pieces);
	}

	private double calculateTotalScore(List<Piece> pieces) {
		double plainSum = pieces.stream()
				.mapToDouble(Piece::getScore).sum();
		return sameColumnPawnHandler(pieces, plainSum);
	}

	private double sameColumnPawnHandler(List<Piece> pieces, double plainSum) {
		List<Pawn> pawns = pieces.stream()
			.filter(p -> p instanceof Pawn)
			.map(p -> (Pawn)p)
			.collect(Collectors.toList());
		for (Pawn pawn : pawns) {
			if (pawns.stream()
					.anyMatch(p -> !p.equals(pawn) && p.isInSameColumn(pawn))) {
				plainSum -= 0.5;
			}
		}
		return plainSum;
	}

	public double getTotalScore() {
		return totalScore;
	}
}
