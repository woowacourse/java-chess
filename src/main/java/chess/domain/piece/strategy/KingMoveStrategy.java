package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Color;
import java.util.List;

public final class KingMoveStrategy extends MoveStrategy {

    @Override
    public boolean isValidateCanMove(Color color, Position from, Position to) {
        List<Direction> directions = Direction.royalDirection(color);

        if (isInvalidDistance(from, to, directions)) {
            throw new IllegalArgumentException("킹이 이동할 수 없는 위치입니다.");
        }

        return true;
    }
}
