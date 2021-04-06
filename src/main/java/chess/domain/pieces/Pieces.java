package chess.domain.pieces;

import chess.domain.position.Position;
import chess.exception.WrongMoveCommandException;

import java.util.ArrayList;
import java.util.List;

public final class Pieces {
    public static final double DEFAULT_SCORE = 0.0;
    public static final double DECREASE_UNIT = 0.5;
    public static final int DECREASE_PIVOT = 2;

    private final List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public boolean containsPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position));
    }

    public Piece pieceByPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findFirst()
                .orElseThrow(WrongMoveCommandException::new);
    }

    public void removePieceByPosition(final Position position) {
        pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findFirst()
                .ifPresent(pieces::remove);
    }

    public boolean isKingAlive() {
        return pieces.stream()
                .anyMatch(Piece::isKing);
    }

    public boolean contains(final Piece piece) {
        return pieces.stream()
                .anyMatch(p -> p.equals(piece));
    }

    public double calculatedScore(final int rangeMinPivot, final int rangeMaxPivot) {
        double simpleSumScore = calculatedSimpleSumScore();
        double decreasedScore = calculatedDecreasedScore(rangeMinPivot, rangeMaxPivot);
        return simpleSumScore - decreasedScore;
    }

    private double calculatedSimpleSumScore() {
        return pieces.stream()
                .mapToDouble(Piece::score)
                .reduce(0, Double::sum);
    }

    private double calculatedDecreasedScore(final int rangeMinPivot, final int rangeMaxPivot) {
        double decreasedScore = DEFAULT_SCORE;
        for (int range = rangeMinPivot; range < rangeMaxPivot; ++range) {
            decreasedScore += decreasedScoreByCol(range);
        }
        return decreasedScore;
    }

    private double decreasedScoreByCol(final int range) {
        long count = pieces.stream()
                .filter(Piece::isPawn)
                .filter(piece -> piece.isSameCol(range))
                .count();
        if (count >= DECREASE_PIVOT) {
            return DECREASE_UNIT * count;
        }
        return DEFAULT_SCORE;
    }

    public List<Piece> toList() {
        return new ArrayList<>(pieces);
    }
}
