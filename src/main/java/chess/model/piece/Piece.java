package chess.model.piece;

import chess.model.ConsoleBoard;
import chess.model.square.Direction;
import chess.model.square.Square;
import java.util.List;

public abstract class Piece {

    private final int id;
    protected final Color color;
    private final int squareId;

    protected Piece(int id, Color color, int squareId) {
        this.id = id;
        this.color = color;
        this.squareId = squareId;
    }

    public Piece(Color color, int squareId) {
        this(0, color, squareId);
    }

    protected Piece(Color color) {
        this(0, color, 0);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean movable(ConsoleBoard consoleBoard, Square source, Square target) {
        return movable(source, target);
    }

    abstract boolean movable(Square source, Square target);

    public boolean isNotAlly(Piece target) {
        return this.color != target.color();
    }

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public Color color() {
        return color;
    }

    public abstract boolean isNotEmpty();

    abstract List<Direction> getDirection();

    public abstract double getPoint();

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract String name();

    public abstract boolean canMoveWithoutObstacle(ConsoleBoard board, Square source, Square target);

    public int getId() {
        return id;
    }

    public int getSquareId() {
        return squareId;
    }
}
