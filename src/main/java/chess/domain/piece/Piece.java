package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

import java.util.Objects;

abstract public class Piece {

    private Color color;
    private MoveStrategy moveStrategy;

    protected Piece(final Color color, final MoveStrategy moveStrategy) {
        this.color = color;
        this.moveStrategy = moveStrategy;
    }

    public boolean isEnemy(final Piece piece) {
        return piece.color != color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return color == piece.color && moveStrategy.equals(piece.moveStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, moveStrategy);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", moveStrategy=" + moveStrategy +
                '}';
    }
}
