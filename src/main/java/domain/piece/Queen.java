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
                UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT
        ));
    }

    public static Queen black() {
        return new Queen(Color.BLACK, Type.QUEEN);
    }

    public static Queen white() {
        return new Queen(Color.WHITE, Type.QUEEN);
    }
}
