package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.board.Row;

import java.util.List;

public final class BlackPawnMovePattern extends AbstractPawnMovePattern {

    private static final Row BLACK_PAWN_START_ROW = Row.RANK_7;

    @Override
    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = getDirections();

        // TODO 상태를 이용해서 리펙터링이 가능할 것 같다
        for (Direction direction : directions) {
            if (src.isStartRow(BLACK_PAWN_START_ROW) && direction.equals(Direction.SOUTH)) {
                if (src.canMoveByTime(direction, dest, 1)) {
                    return direction;
                }
                if (src.canMoveByTime(direction, dest, 2)) {
                    return direction;
                }
            } else if (src.canMoveByTime(direction, dest, 1)) {
                return direction;
            }
        }
        return null;
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.getBlackPawnDirections();
    }
}
