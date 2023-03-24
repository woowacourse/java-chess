package chess.domain.board;

import static java.util.Map.entry;

import chess.domain.piece.PieceType;
import java.util.Map;
import java.util.Objects;

public final class Score {

    private static final Map<PieceType, Score> pieceScoreMapper = Map.ofEntries(
            entry(PieceType.PAWN, new Score(1)),
            entry(PieceType.KNIGHT, new Score(2.5)),
            entry(PieceType.BISHOP, new Score(3)),
            entry(PieceType.ROOK, new Score(5)),
            entry(PieceType.QUEEN, new Score(9)),
            entry(PieceType.KING, new Score(0))
    );
    static final int DUPLICATE_SAME_FILE_PAWN_LIMIT = 2;
    static final Score ZERO = new Score(0);
    static final double PAWN_WEIGHT = 0.5;

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public static Score mapPieceScore(final PieceType pieceType) {
        return pieceScoreMapper.get(pieceType);
    }

    public Score sum(final Score addition) {
        return new Score(addition.value + this.value);
    }

    public Score subtract(final Score subtrahend) {
        return new Score(this.value - subtrahend.value);
    }

    public Score multiply(final double value) {
        return new Score(this.value * value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score score = (Score) o;
        return Double.compare(score.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public double getValue() {
        return value;
    }
}
