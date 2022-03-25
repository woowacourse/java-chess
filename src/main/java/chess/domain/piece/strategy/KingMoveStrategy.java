package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.List;

public class KingMoveStrategy extends MoveStrategy {

    @Override
    public boolean isValidateCanMove(Color color, Position from, Position to) {
        List<Direction> directions = Direction.everyDirection(color);

        if (!isValidDistance(from, to, directions)) {
            throw new IllegalArgumentException("킹이 이동할 수 없는 위치입니다.");
        }

        return true;
    }

    private boolean isValidDistance(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .anyMatch(direction -> direction.isSameDistance(from, to));
    }
}
