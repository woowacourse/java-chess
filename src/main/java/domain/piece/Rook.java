package domain.piece;

import domain.Player;
import domain.direction.Direction;
import java.util.List;

public class Rook extends MovableRangePiece {

    private static final List<Direction> DIRECTIONS = List.of(
        Direction.EAST,
        Direction.WEST,
        Direction.SOUTH,
        Direction.NORTH
    );

    public Rook(final Player player) {
        super(player, PieceSymbol.ROOK);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }

    @Override
    public double score(final boolean isSeveralPawn) {
        return PieceScore.ROOK.score(isSeveralPawn);
    }
}
