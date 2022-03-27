package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import java.util.List;

public class KingMoveStrategy extends MoveStrategy {

    @Override
    public boolean isValidateCanMove(Color color, Position from, Position to) {
        List<Direction> directions = Direction.everyDirection(color);

        if (isInvalidDistance(from, to, directions)) {
            throw new IllegalArgumentException("킹이 이동할 수 없는 위치입니다.");
        }

        return true;
    }
}
