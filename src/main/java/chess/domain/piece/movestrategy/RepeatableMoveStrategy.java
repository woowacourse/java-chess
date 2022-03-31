package chess.domain.piece.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

public class RepeatableMoveStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Board board, Coordinate from, Coordinate to) {
        Direction direction = Direction.of(from, to);

        return isNotObstacleExist(board, direction, from, to);
    }

    private boolean isNotObstacleExist(Board board, Direction direction, Coordinate from,
                                       Coordinate to) {
        Coordinate nextCoordinate = from.next(direction);
        if (nextCoordinate == to) {
            return true;
        }

        Piece piece = board.findPiece(nextCoordinate);
        if (piece.isExist()) {
            return false;
        }

        return isNotObstacleExist(board, direction, nextCoordinate, to);
    }

}
