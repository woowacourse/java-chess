package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

import java.util.Objects;

public class RealPiece extends Piece {
    private final Color color;

    public RealPiece(Color color, String notation, MoveStrategy moveStrategy) {
        super(notation, moveStrategy);
        this.color = color;
    }

    @Override
    public String getNotation() {
        return color.changeNotation(super.getNotation());
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealPiece realPiece = (RealPiece) o;
        return color == realPiece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
