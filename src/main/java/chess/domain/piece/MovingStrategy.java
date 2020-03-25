package chess.domain.piece;

import chess.domain.Direction;

import java.util.List;

public abstract class MovingStrategy {
    private static int maximumDistance;
    private static List<Direction> movableDirections;
}
