package chess.domain.piece.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

public class PawnMoveStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Board board, Coordinate from, Coordinate to) {
        Direction direction = Direction.of(from, to);

        if (direction.isVertical()) {
            return moveVertical(board, from, to, direction);
        }

        return moveDiagonal(board, from, to, direction);
    }

    private boolean moveVertical(Board board, Coordinate from, Coordinate to, Direction direction) {
        Coordinate nextCoordinate = from.next(direction);
        Piece nextPiece = board.findPiece(nextCoordinate);
        if (nextPiece.isExist()) {
            return false;
        }
        return nextCoordinate == to || moveTwice(board, from, to, direction);
    }

    private boolean moveTwice(Board board, Coordinate from, Coordinate to, Direction direction) {
        if (from.isPawnStartRow()) {
            Coordinate nextCoordinate = from.next(direction)
                    .next(direction);
            Piece nextPiece = board.findPiece(nextCoordinate);
            return nextPiece.isEmpty() && nextCoordinate == to;
        }
        return false;
    }

    private boolean moveDiagonal(Board board, Coordinate from, Coordinate to, Direction direction) {
        Coordinate nextCoordinate = from.next(direction);
        Piece toPiece = board.findPiece(to);
        return toPiece.isExist() && nextCoordinate == to;
    }
}
