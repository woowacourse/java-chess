package chess.domain.piece.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;

public class NonRepeatableMoveStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Board board, Coordinate from, Coordinate to) {
        Direction direction = Direction.of(from, to);

        return from.next(direction) == to;
    }
}
