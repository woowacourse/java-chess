package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Color;
import java.util.List;

public final class QueenMoveStrategy extends MoveStrategy {

    private static final String NO_MOVE_MESSAGE = "퀸이 이동할 수 없는 위치입니다.";

    @Override
    public boolean isValidateCanMove(Color color, Position from, Position to) {
        List<Direction> directions = Direction.royalDirection(color);

        if (isInvalidDirection(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }

        return true;
    }
}
