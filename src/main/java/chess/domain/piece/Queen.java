package chess.domain.piece;

import chess.domain.piece.direction.Direction;
import chess.domain.piece.position.Position;
import java.util.List;
import java.util.Objects;

public class Queen extends Piece {

    private static final double SCORE = 9;

    private static final String BLACK_DISPLAY = "♕";
    private static final String WHITE_DISPLAY = "♛";

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getPositionsInPath(Position toPosition) {
        Direction direction = Direction.findAllDirection(position, toPosition);
        return direction.findPositionsInPath(position, toPosition);
    }

    @Override
    public void move(Position position) {
        validateMovable(position);
        this.position = position;
    }

    @Override
    public void validateMovable(Position toPosition) {
        if (!isMovablePosition(toPosition)) {
            throw new IllegalArgumentException(INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean isMovablePosition(Position toPosition) {
        return position.isHorizontal(toPosition)
            || position.isVertical(toPosition)
            || position.isDiagonal(toPosition);
    }

    @Override
    protected void attack(Position enemyPosition) {
        move(enemyPosition);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public String display() {
        if (color == Color.BLACK) {
            return BLACK_DISPLAY;
        }
        return WHITE_DISPLAY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Queen queen = (Queen) o;
        return color == queen.color
            && position == queen.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Queen{" +
            "color=" + color +
            ", position=" + position +
            '}';
    }
}
