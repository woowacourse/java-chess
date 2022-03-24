package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.List;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public void canMove(Color color, Position from, Position to) {
        List<Direction> directions = Direction.knightDirection(color);

        if (!isValidDistance(from, to, directions)) {
            throw new IllegalArgumentException("나이트가 이동할 수 없는 위치입니다.");
        }
    }

    private boolean isValidDistance(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .anyMatch(direction -> direction.isSameDistance(from, to));
    }
}
