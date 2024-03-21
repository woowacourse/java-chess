package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.strategy.MoveStrategy;
import java.util.List;

public abstract class Piece {
    private final Color color;
    private final Type type;

    protected Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public Type type() {
        return type;
    }

    public Color color() {
        return color;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public abstract MoveStrategy strategy();

    public boolean isNotNone() {
        return type != Type.NONE;
    }

    public abstract List<Direction> movableDirections();
}
