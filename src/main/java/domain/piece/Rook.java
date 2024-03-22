package domain.piece;

import static domain.piece.info.Direction.DOWN;
import static domain.piece.info.Direction.LEFT;
import static domain.piece.info.Direction.RIGHT;
import static domain.piece.info.Direction.UP;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.strategy.MoveStrategy;
import domain.strategy.SelectiveMoveStrategy;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    public MoveStrategy strategy() {
        return new SelectiveMoveStrategy();
    }

    @Override
    public List<Direction> movableDirections() {
        return new ArrayList<>(List.of(
                UP, RIGHT, DOWN, LEFT
        ));
    }

    public static Rook black() {
        return new Rook(Color.BLACK, Type.ROOK);
    }

    public static Rook white() {
        return new Rook(Color.WHITE, Type.ROOK);
    }
}
