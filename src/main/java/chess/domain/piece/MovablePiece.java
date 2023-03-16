package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Role;
import chess.domain.Side;

import java.util.List;

import static chess.domain.piece.Direction.*;
import static chess.domain.piece.Direction.SOUTH_WEST;

public abstract class MovablePiece extends Piece {
    protected static final List<Direction> STRAIGHT_DIRECTIONS = List.of(NORTH, WEST, SOUTH, EAST);
    protected static final List<Direction> DIAGONAL_DIRECTIONS = List.of(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);

    protected final Side side;
    protected final Role role;

    public MovablePiece(final Side side, final Role role) {
        this.side = side;
        this.role = role;
    }

    public boolean isSameSide(final Side side) {
        return this.side.equals(side);
    }

    protected boolean isOpponentSide(final MovablePiece targetPiece) {
        return !side.equals(targetPiece.side);
    }

    protected boolean isStraight(final Direction direction) {
        return STRAIGHT_DIRECTIONS.contains(direction);
    }

    protected boolean isDiagonal(final Direction direction) {
        return DIAGONAL_DIRECTIONS.contains(direction);
    }

    public Side getSide() {
        return side;
    }

    public Color getColor() {
        return side.getColor();
    }

    public abstract boolean canAttack(final Direction direction, final int distance, final MovablePiece piece);


    public abstract boolean canMove(final Direction direction, final int distance);
}
