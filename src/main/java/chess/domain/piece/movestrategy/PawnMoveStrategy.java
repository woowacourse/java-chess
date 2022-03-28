package chess.domain.piece.movestrategy;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;
import java.util.Map;

public class PawnMoveStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Map<Coordinate, Piece> value, Coordinate from, Coordinate to) {
        Direction direction = Direction.of(from, to);

        if (direction.isVertical()) {
            return moveVertical(value, from, to, direction);
        }

        return moveDiagonal(value, from, to, direction);
    }

    private boolean moveVertical(Map<Coordinate, Piece> value, Coordinate from, Coordinate to, Direction direction) {
        Coordinate nextCoordinate = from.next(direction);
        Piece nextPiece = value.get(nextCoordinate);
        if (!nextPiece.isEmpty()) {
            return false;
        }
        return nextCoordinate == to || moveTwice(value, from, to, direction);
    }

    private boolean moveTwice(Map<Coordinate, Piece> value, Coordinate from, Coordinate to, Direction direction) {
        if (from.isPawnStartRow()) {
            Coordinate nextCoordinate = from.next(direction)
                    .next(direction);
            Piece nextPiece = value.get(nextCoordinate);
            return nextPiece.isEmpty() && nextCoordinate == to;
        }
        return false;
    }

    private boolean moveDiagonal(Map<Coordinate, Piece> value, Coordinate from, Coordinate to, Direction direction) {
        Coordinate nextCoordinate = from.next(direction);
        Piece toPiece = value.get(to);
        return !toPiece.isEmpty() && nextCoordinate == to;
    }
}
