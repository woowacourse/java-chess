package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.strategy.MoveStrategy;
import java.util.List;

public class None extends Piece {

    public None(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    public MoveStrategy strategy() {
        return null;
    }

    @Override
    public List<Direction> movableDirections() {
        return null;
    }

    public static None none() {
        return new None(Color.NONE, Type.NONE);
    }
}
