package chess.model.piece;

import chess.Board;
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

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public abstract String name();

    public abstract boolean movable(Square source, Square target);

    public boolean movable(Board board, Square source, Square target) {
        return movable(source, target);
    };

    public abstract boolean isObstacleOnRoute(Board board, Square source, Square target);

    abstract List<Direction> getDirection();

    protected boolean isNotAlly(Piece target) {
        return this.color != target.color;
    }

    public abstract boolean isNotEmpty();

    public abstract double getPoint();

    public abstract boolean isPawn();

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }
}
