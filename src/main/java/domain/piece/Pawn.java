package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.piece.state.PawnInit;
import domain.piece.state.PawnMoved;
import domain.piece.state.State;
import domain.strategy.MoveStrategy;
import domain.strategy.PawnMoveStrategy;
import java.util.List;

public class Pawn extends Piece {
    private State state;

    public Pawn(final Color color, final Type type) {
        super(color, type);
        this.state = new PawnInit();
    }

    @Override
    public MoveStrategy strategy() {
        return new PawnMoveStrategy();
    }

    @Override
    public List<Direction> movableDirections() {
        final List<Direction> directions = this.state.movableDirection(color());
        this.state = new PawnMoved();
        return directions;
    }

    public static Pawn black() {
        return new Pawn(Color.BLACK, Type.PAWN);
    }

    public static Pawn white() {
        return new Pawn(Color.WHITE, Type.PAWN);
    }
}
