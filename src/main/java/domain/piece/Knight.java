package domain.piece;

import static domain.piece.info.Direction.DOWN_DOWN_LEFT;
import static domain.piece.info.Direction.DOWN_DOWN_RIGHT;
import static domain.piece.info.Direction.DOWN_LEFT_LEFT;
import static domain.piece.info.Direction.DOWN_RIGHT_RIGHT;
import static domain.piece.info.Direction.UP_LEFT_LEFT;
import static domain.piece.info.Direction.UP_RIGHT_RIGHT;
import static domain.piece.info.Direction.UP_UP_LEFT;
import static domain.piece.info.Direction.UP_UP_RIGHT;

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
                UP_UP_LEFT, UP_UP_RIGHT, UP_LEFT_LEFT, UP_RIGHT_RIGHT,
                DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT, DOWN_LEFT_LEFT, DOWN_RIGHT_RIGHT
        ));
    }

    public static Knight black() {
        return new Knight(Color.BLACK, Type.KNIGHT);
    }

    public static Knight white() {
        return new Knight(Color.WHITE, Type.KNIGHT);
    }
}
