package chess.domain.pieces;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public final class Pieces {
    public static final double DEFAULT_SCORE = 0.0;
    public static final double DECREASE_UNIT = 0.5;
    public static final int DECREASE_PIVOT = 2;

    private final List<Piece> pieces;

    public Pieces(List<? extends Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public boolean containByPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.samePosition(position));
    }

    public final Piece getPieceByPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.samePosition(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("움직이려는 말이 본인의 말이 아닙니다."));
    }

    public void removePieceByPosition(final Position position) {
        pieces.stream()
                .filter(piece -> piece.samePosition(position))
                .findFirst()
                .ifPresent(pieces::remove);
    }

    public final List<Piece> toList() {
        return new ArrayList<>(pieces);
    }

    public final boolean kingAlive() {
        return pieces.stream()
                .anyMatch(Piece::isKing);
    }

    public double calculateScore(final int rangeMinPivot, final int rangeMaxPivot) {
        double simpleSumScore = calculateSimpleSumScore();
        double decreasedScore = calculateDecreasedScore(rangeMinPivot, rangeMaxPivot);
        return simpleSumScore - decreasedScore;
    }

    private double calculateSimpleSumScore() {
        return pieces.stream()
                .mapToDouble(Piece::score)
                .reduce(0, Double::sum);
    }

    private double calculateDecreasedScore(final int rangeMinPivot, final int rangeMaxPivot) {
        double decreasedScore = DEFAULT_SCORE;
        for (int range = rangeMinPivot; range < rangeMaxPivot; ++range) {
            decreasedScore += calculateRange(range);
        }
        return decreasedScore;
    }

    private double calculateRange(final int range) {
        long count = pieces.stream()
                .filter(Piece::isPawn)
                .filter(piece -> piece.sameCol(range))
                .count();
        if (count >= DECREASE_PIVOT) {
            return DECREASE_UNIT * count;
        }
        return DEFAULT_SCORE;
    }
}
