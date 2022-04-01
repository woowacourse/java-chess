package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pieces {

    private final List<Piece> value;

    public Pieces(final List<Piece> value) {
        this.value = value;
    }

    public List<Piece> getValue() {
        return Collections.unmodifiableList(value);
    }

    public long getPawnCount() {
        return value.stream()
                .filter(p -> p.isSameSymbol(Symbol.PAWN))
                .count();
    }

    public double getSumOfScore() {
        return value.stream()
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pieces pieces = (Pieces) o;
        return Objects.equals(value, pieces.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
