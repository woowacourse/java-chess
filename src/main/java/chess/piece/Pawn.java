package chess.piece;

import chess.chessgame.MovingPosition;
import chess.chessgame.Position;

import java.util.List;

public class Pawn extends Piece {

    private static final int X_OF_FIST_WHITE = 6;
    private static final int X_OF_FIST_BLACK = 1;
    private static final List<Position> COORDINATES_OF_WHITE_DIAGONAL_MOVABLE = List.of(
            new Position(-1, -1),
            new Position(-1, 1));
    private static final List<Position> COORDINATES_OF_BLACK_DIAGONAL_MOVABLE = List.of(
            new Position(1, -1),
            new Position(1, 1));
    private static final List<Position> COORDINATES_OF_WHITE_FIRST_TURN_MOVABLE = List.of(
            new Position(-1, 0),
            new Position(-2, 0));
    private static final List<Position> COORDINATES_OF_BLACK_FIRST_TURN_MOVABLE = List.of(
            new Position(1, 0),
            new Position(2, 0));
    private static final List<Position> COORDINATES_OF_WHITE_TURN_MOVABLE = List.of(
            new Position(-1, 0));
    private static final List<Position> COORDINATES_OF_BLACK_TURN_MOVABLE = List.of(
            new Position(1, 0));

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    @Override
    public boolean isMovable(MovingPosition movingPosition) {
        if (color == Color.WHITE) {
            return isMovableWhenWhite(movingPosition);
        }

        if (color == Color.BLACK) {
            return isMovableWhenBlack(movingPosition);
        }
        return false;
    }

    @Override
    public List<Position> computeMiddlePosition(MovingPosition movingPosition) {
        return movingPosition.computeLinearMiddle();
    }

    public boolean isDiagonal(MovingPosition movingPosition) {
        if (color == Color.WHITE) {
            return movingPosition.isAnyPossible(COORDINATES_OF_WHITE_DIAGONAL_MOVABLE);
        }
        if (color == Color.BLACK) {
            return movingPosition.isAnyPossible(COORDINATES_OF_BLACK_DIAGONAL_MOVABLE);
        }
        return false;
    }

    private boolean isMovableWhenWhite(MovingPosition movingPosition) {
        if (movingPosition.isSameFromX(X_OF_FIST_WHITE)) {
            return movingPosition.isAnyPossible(COORDINATES_OF_WHITE_FIRST_TURN_MOVABLE);
        }
        return movingPosition.isAnyPossible(COORDINATES_OF_WHITE_TURN_MOVABLE);
    }

    private boolean isMovableWhenBlack(MovingPosition movingPosition) {
        if (movingPosition.isSameFromX(X_OF_FIST_BLACK)) {
            return movingPosition.isAnyPossible(COORDINATES_OF_BLACK_FIRST_TURN_MOVABLE);
        }
        return movingPosition.isAnyPossible(COORDINATES_OF_BLACK_TURN_MOVABLE);
    }

}