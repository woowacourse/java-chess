package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Color;
import java.util.List;


public final class BishopMoveStrategy extends MoveStrategy {

    private static final String NO_MOVE_MESSAGE = "비숍이 이동할 수 없는 위치입니다.";

    @Override
    public boolean isValidateCanMove(Color color, Position from, Position to) {
        List<Direction> directions = bishopDirection(color);

        if (isInvalidDirection(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }

        return true;
    }

    public static List<Direction> bishopDirection(Color color) {
        return Direction.getColorDirections(color, List.of(Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT));
    }
}
