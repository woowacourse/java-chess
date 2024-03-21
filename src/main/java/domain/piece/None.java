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
        throw new UnsupportedOperationException("비어 있는 칸입니다.");
    }

    @Override
    public List<Direction> movableDirections() {
        throw new UnsupportedOperationException("비어 있는 칸입니다.");
    }

    public static None none() {
        return new None(Color.NONE, Type.NONE);
    }
}
