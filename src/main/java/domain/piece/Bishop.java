package domain.piece;

import static domain.piece.info.Direction.DOWN_LEFT;
import static domain.piece.info.Direction.DOWN_RIGHT;
import static domain.piece.info.Direction.UP_LEFT;
import static domain.piece.info.Direction.UP_RIGHT;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.strategy.MoveStrategy;
import domain.strategy.SelectiveMoveStrategy;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    public MoveStrategy strategy() {
        return new SelectiveMoveStrategy();
    }

    @Override
    public List<Direction> movableDirections() {
        return new ArrayList<>(List.of(
                UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
        ));
    }

    public static Bishop black() {
        return new Bishop(Color.BLACK, Type.BISHOP);
    }

    public static Bishop white() {
        return new Bishop(Color.WHITE, Type.BISHOP);
    }
}
