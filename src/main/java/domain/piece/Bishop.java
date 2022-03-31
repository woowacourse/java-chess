package domain.piece;

import domain.Player;
import domain.direction.Direction;
import java.util.List;

public class Bishop extends MovableRangePiece {

    private static final List<Direction> DIRECTIONS = List.of(
        Direction.NORTHEAST,
        Direction.NORTHWEST,
        Direction.SOUTHEAST,
        Direction.SOUTHWEST
    );

    public Bishop(final Player player) {
        super(player, PieceSymbol.BISHOP);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }

    @Override
    public double score(boolean isSeveralPawn) {
        return PieceScore.BISHOP.score(isSeveralPawn);
    }
}
