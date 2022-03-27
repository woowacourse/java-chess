package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import java.util.ArrayList;
import java.util.List;

public class KnightMoveStrategy extends MoveStrategy {

    @Override
    public boolean isValidateCanMove(Color color, Position from, Position to) {
        List<Direction> directions = knightDirection(color);

        if (isInvalidDistance(from, to, directions)) {
            throw new IllegalArgumentException("나이트가 이동할 수 없는 위치입니다.");
        }

        return true;
    }

    @Override
    public List<Position> getRoute(Position from, Position to) {
        return new ArrayList<>();
    }

    public static List<Direction> knightDirection(Color color) {
        return Direction.getColorDirections(color, List.of(Direction.TTR, Direction.RRT, Direction.RRD, Direction.DDR,
                Direction.DDL, Direction.LLD, Direction.LLT, Direction.TTL));
    }
}
