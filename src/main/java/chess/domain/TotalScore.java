package chess.domain;

import chess.domain.piece.Piece;

import java.util.List;

public class TotalScore {
    private final double totalScore;

    public TotalScore(List<Piece> pieces) {
        this.totalScore = calculateTotalScore(pieces);
    }

    private double calculateTotalScore(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(piece -> piece.getScore(pieces)).sum();
    }

    public double getTotalScore() {
        return totalScore;
    }
}
