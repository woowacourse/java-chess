package chess.domain.piece;

import java.util.List;

public class Pieces {

    private final List<Piece> value;

    public Pieces(final List<Piece> value) {
        this.value = value;
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
}
