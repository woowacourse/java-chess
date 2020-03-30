package chess.domain.game;

import java.util.List;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class Score {
    private static final double PAWN_HALF_SCORE = 0.5;
    private final double score;

    public Score(double score) {
        this.score = score;
    }

    public static Score calculate(List<Piece> pieces) {
        return new Score(totalScore(pieces) - pawnScore(pieces));
    }

    private static double pawnScore(List<Piece> pieces) {
        int count = 0;
        for (int x = Position.START_POSITION_X; x < Position.END_POSITION_X; x++) {
            count += countPawnOnSameFile(pieces, x);
        }
        return count * PAWN_HALF_SCORE;
    }

    private static int countPawnOnSameFile(List<Piece> pieces, int x) {
        int count = (int)pieces.stream()
            .filter(piece -> piece.isPawn() && piece.getPosition().equalsX(x))
            .count();
        if (count >= 2) {
            return count;
        }
        return 0;
    }

    private static double totalScore(List<Piece> pieces) {
        return pieces.stream().mapToDouble(Piece::score).sum();
    }

    public boolean isHigherThan(Score that) {
        return this.score > that.score;
    }

    public double getValue() {
        return score;
    }
}
