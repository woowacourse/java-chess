package chess.domain.strategy;

import chess.domain.Direction;
import java.util.List;

public class RookMoveStrategy extends SpecialPieceMoveStrategy{

    private static final int DEFAULT_MAX_MOVE_DISTANCE = 7;
    private static final List<Direction> DIRECTIONS = List.of(Direction.N, Direction.W, Direction.S, Direction.E);

    public RookMoveStrategy() {
        super(DIRECTIONS, DEFAULT_MAX_MOVE_DISTANCE);
    }
}
