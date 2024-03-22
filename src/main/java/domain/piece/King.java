package domain.piece;

import static domain.piece.info.Direction.DOWN;
import static domain.piece.info.Direction.DOWN_LEFT;
import static domain.piece.info.Direction.DOWN_RIGHT;
import static domain.piece.info.Direction.LEFT;
import static domain.piece.info.Direction.RIGHT;
import static domain.piece.info.Direction.UP;
import static domain.piece.info.Direction.UP_LEFT;
import static domain.piece.info.Direction.UP_RIGHT;

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

    public static King black() {
        return new King(Color.BLACK, Type.KING);
    }

    public static King white() {
        return new King(Color.WHITE, Type.KING);
    }

    @Override
    public List<Direction> movableDirections() {
        return new ArrayList<>(List.of(
                UP, UP_RIGHT, UP_LEFT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT
        ));
    }
}
