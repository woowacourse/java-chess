package chess.domain.piece;

import chess.domain.Color;
import chess.practiceMove.Direction;

import java.util.List;
import java.util.Map;

public class Knight extends Piece {
    private static final String name = "n";
    private static final Map<Integer, Integer> movableDistance = Map.of(1, 2, 2, 1);
    private static final List<Direction> movableDirection = List.of(Direction.KNIGHT_TOP_LEFT,
            Direction.KNIGHT_TOP_RIGHT,
            Direction.KNIGHT_LEFT_TOP,
            Direction.KNIGHT_LEFT_BOTTOM,
            Direction.KNIGHT_RIGHT_TOP,
            Direction.KNIGHT_RIGHT_BOTTOM,
            Direction.KNIGHT_BOTTOM_LEFT,
            Direction.KNIGHT_BOTTOM_RIGHT);


    public Knight(Color color) {
        super(name, color);
    }

    @Override
    public boolean isMovableAtOnce(int absGapOfColumn, int absGapOfRank) {
        return movableDistance.entrySet()
                .stream()
                .anyMatch((entry) -> entry.getKey() == absGapOfColumn && entry.getValue() == absGapOfRank);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return movableDirection.contains(direction);
    }
}
