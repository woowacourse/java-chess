package chess.domain.piece;

import chess.domain.order.MoveOrder;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.strategy.MoveStrategy;

import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Notation notation;
    private final MoveStrategy moveStrategy;

    public Piece(Color color, Notation notation, MoveStrategy moveStrategy) {
        this.color = color;
        this.notation = notation;
        this.moveStrategy = moveStrategy;
    }

    public boolean canMove(MoveOrder moveOrder) {
        return this.moveStrategy.canMove(moveOrder);
    }

    public boolean isNotBlank() {
        return !this.equals(Blank.getInstance());
    }

    public String getNotationText() {
        return notation.getNotationText(color);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor());
    }
}
