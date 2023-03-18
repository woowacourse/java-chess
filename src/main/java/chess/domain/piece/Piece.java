package chess.domain.piece;

import chess.domain.board.Position;

import java.util.*;

public abstract class Piece {

    protected static final String CAN_NOT_MOVE_EXCEPTION_MESSAGE = "갈 수 없는 위치입니다.";


    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    protected abstract Set<Position> computePath(Position source, Position target);

    public abstract Kind getKind();

    public Set<Position> computePathWithValidate(Position source, Position target) {
        validateSamePosition(source, target);
        return computePath(source, target);
    }

    private void validateSamePosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }

    public abstract boolean canMove(Map<Position, Boolean> isEmptyPosition, Position source, Position target);

    public boolean isEmpty() {
        return false;
    }

    public boolean equalsColor(final Piece targetSquare) {
        return color == targetSquare.color;
    }

    public boolean equalsColor(final Color color) {
        return this.color == color;
    }

    public Color getColor() {
        return color;
    }
}
