package chess.model.piece;

import chess.Direction;
import chess.model.square.Square;
import java.util.List;

public abstract class Piece {

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                '}';
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public abstract String name();

    public abstract boolean movable(Square source, Square target);

    abstract List<Direction> getDirection();
}
