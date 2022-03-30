package chess.piece;

import chess.piece.movementcondition.MovementCondition;
import chess.position.Position;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, getClass());
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + getClass().getSimpleName();
    }

    public abstract MovementCondition identifyMovementCondition(Position from, Position to);

    public abstract BigDecimal getPoint();
}
