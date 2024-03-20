package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.strategy.MoveStrategy;
import domain.strategy.PawnMoveStrategy;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    public MoveStrategy strategy() {
        return new PawnMoveStrategy();
    }

    @Override
    public List<Direction> movableDirections() {
        return new ArrayList<>(List.of(
                Direction.UP,
                Direction.UP_RIGHT,
                Direction.UP_LEFT
        ));
    }

    public static Pawn black() {
        return new Pawn(Color.BLACK, Type.PAWN);
    }

    public static Pawn white() {
        return new Pawn(Color.WHITE, Type.PAWN);
    }
}
