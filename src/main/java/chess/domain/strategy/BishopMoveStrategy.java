package chess.domain.strategy;

import chess.domain.Direction;
import java.util.List;

public class BishopMoveStrategy extends SpecialPieceMoveStrategy{

    private static final int DEFAULT_MAX_MOVE_DISTANCE = 7;

    private static final List<Direction> DIRECTIONS = List.of(Direction.NE, Direction.SE, Direction.SW, Direction.NW);

    public BishopMoveStrategy() {
        super(DIRECTIONS, DEFAULT_MAX_MOVE_DISTANCE);
    }
}
