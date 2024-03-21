package chess.domain.strategy;

import chess.domain.Direction;
import java.util.List;

public class KnightMoveStrategy extends SpecialPieceMoveStrategy{

    private static final int DEFAULT_MAX_MOVE_DISTANCE = 1;

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.NNE, Direction.ENE, Direction.ESE, Direction.SSE,
            Direction.SSW, Direction.WSW, Direction.WNW, Direction.NNW
    );


    public KnightMoveStrategy() {
        super(DIRECTIONS, DEFAULT_MAX_MOVE_DISTANCE);
    }
}
