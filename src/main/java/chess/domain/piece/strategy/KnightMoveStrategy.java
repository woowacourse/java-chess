package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Team;
import java.util.ArrayList;
import java.util.List;

public final class KnightMoveStrategy extends MoveStrategy {

    private static final String NO_MOVE_MESSAGE = "나이트가 이동할 수 없는 위치입니다.";

    public static List<Direction> knightDirection(Team team) {
        return Direction.getAbsoluteDirections(team, List.of(Direction.TTR, Direction.RRT, Direction.RRD, Direction.DDR,
                Direction.DDL, Direction.LLD, Direction.LLT, Direction.TTL));
    }

    @Override
    public boolean isValidateCanMove(Team team, Position from, Position to) {
        List<Direction> directions = knightDirection(team);

        if (isInvalidDistance(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }

        return true;
    }

    @Override
    public List<Position> getRoute(Position from, Position to) {
        return new ArrayList<>();
    }
}
