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

    public boolean movable(Piece targetPiece, Square source, Square target) {
        return movable(source, target);
    }

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

    public abstract List<Square> getRoute(Square source, Square target);

    public int getId() {
        return id;
    }

    public int getSquareId() {
        return squareId;
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

        if (id != piece.id) {
            return false;
        }
        if (squareId != piece.squareId) {
            return false;
        }
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + squareId;
        return result;
    }
}
