package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.strategy.MoveStrategy;
import domain.strategy.SelectiveMoveStrategy;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    public MoveStrategy strategy() {
        return new SelectiveMoveStrategy();
    }

    @Override
    public List<Direction> movableDirections() {
        return new ArrayList<>(List.of(
                Direction.UP,
                Direction.UP_RIGHT,
                Direction.RIGHT,
                Direction.DOWN_RIGHT,
                Direction.DOWN,
                Direction.DOWN_LEFT,
                Direction.LEFT,
                Direction.UP_LEFT
        ));
    }

    public static Queen black() {
        return new Queen(Color.BLACK, Type.QUEEN);
    }

    public static Queen white() {
        return new Queen(Color.WHITE, Type.QUEEN);
    }
}
