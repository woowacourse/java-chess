package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.strategy.AbsoluteMoveStrategy;
import domain.strategy.MoveStrategy;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    public MoveStrategy strategy() {
        return new AbsoluteMoveStrategy();
    }

    @Override
    public List<Direction> movableDirections() {
        return new ArrayList<>(List.of(
                Direction.UP,
                Direction.UP_RIGHT,
                Direction.UP_LEFT,
                Direction.RIGHT,
                Direction.DOWN_RIGHT,
                Direction.DOWN,
                Direction.DOWN_LEFT,
                Direction.LEFT
        ));
    }

    public static King black() {
        return new King(Color.BLACK, Type.KING);
    }

    public static King white() {
        return new King(Color.WHITE, Type.KING);
    }
}
