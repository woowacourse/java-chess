package chess.domain;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class TotalScore {
	private final Pieces pieces;
	private final double totalScore;

	public TotalScore(Pieces pieces) {
		this.pieces = pieces;
		this.totalScore = calculateTotalScore();
	}

	private double calculateTotalScore() {
		double plainSum = pieces.getAlivePieces().stream().mapToDouble(Piece::getScore).sum();
		return sameColumnPawnHandler(plainSum);
	}

	private double sameColumnPawnHandler(double plainSum) {
		List<Pawn> pawns = pieces.getAlivePieces().stream()
			.filter(p -> p instanceof Pawn)
			.map(p -> (Pawn)p)
			.collect(Collectors.toList());
		for (Pawn pawn : pawns) {
			if (pawns.stream().anyMatch(p -> !p.equals(pawn) && p.isInSameColumn(pawn))) {
				plainSum -= 0.5;
			}
		}
		return plainSum;
	}

	public double getTotalScore() {
		return totalScore;
	}
}
