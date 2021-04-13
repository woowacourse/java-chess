package chess.domain.piece;

import chess.domain.order.MoveRoute;
import chess.domain.piece.strategy.MoveStrategy;

import java.util.Objects;

public class RealPiece extends Piece {
    private final Color color;
    private final MoveStrategy moveStrategy;

    public RealPiece(Color color, String name, MoveStrategy moveStrategy) {
        super(name);
        this.color = color;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public boolean canMove(MoveRoute moveRoute) {
        return this.moveStrategy.canMove(moveRoute);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean isSameColor(Piece piece) {
        return this.isSameColor(piece.getColor());
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
