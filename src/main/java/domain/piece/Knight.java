package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.strategy.AbsoluteMoveStrategy;
import domain.strategy.MoveStrategy;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    public MoveStrategy strategy() {
        return new AbsoluteMoveStrategy();
    }

    @Override
    public List<Direction> movableDirections() {
        return new ArrayList<>(List.of(
                Direction.UP_UP_LEFT,
                Direction.UP_UP_RIGHT,
                Direction.UP_LEFT_LEFT,
                Direction.UP_RIGHT_RIGHT,
                Direction.DOWN_DOWN_LEFT,
                Direction.DOWN_DOWN_RIGHT,
                Direction.DOWN_LEFT_LEFT,
                Direction.DOWN_RIGHT_RIGHT
        ));
    }

    public static Knight black() {
        return new Knight(Color.BLACK, Type.KNIGHT);
    }

    public static Knight white() {
        return new Knight(Color.WHITE, Type.KNIGHT);
    }
}
