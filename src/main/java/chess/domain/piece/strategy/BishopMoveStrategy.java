package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.List;


public class BishopMoveStrategy implements MoveStrategy {
    @Override
    public void canMove(Color color, Position from, Position to) {
        List<Direction> directions = Direction.bishopDirection(color);

        if (!isValidDirection(from, to, directions)) {
            throw new IllegalArgumentException("비숍이 이동할 수 없는 위치입니다.");
        }
    }

    private boolean isValidDirection(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .anyMatch(direction -> direction.isSameDirection(from, to));
    }
}
