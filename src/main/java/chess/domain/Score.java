package chess.domain;

import static java.util.Map.entry;

import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;
import java.util.Objects;

public final class Score {

    private static final Map<Class<? extends Piece>, Score> pieceScoreMapper = Map.ofEntries(
            entry(Pawn.class, new Score(1)),
            entry(Knight.class, new Score(2.5)),
            entry(Bishop.class, new Score(3)),
            entry(Rook.class, new Score(5)),
            entry(Queen.class, new Score(9))
    );

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public static Score pieceMapScore(final Class<? extends Piece> pieceClass) {
        return pieceScoreMapper.get(pieceClass);
    }

    public Score sum(final Score score) {
        return new Score(score.value + this.value);
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
