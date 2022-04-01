package domain.piece;

import domain.Player;
import domain.direction.Direction;
import java.util.List;

public class Queen extends MovableRangePiece {

    private static final List<Direction> DIRECTIONS = List.of(
        Direction.NORTHEAST,
        Direction.NORTHWEST,
        Direction.SOUTHEAST,
        Direction.SOUTHWEST,
        Direction.EAST,
        Direction.WEST,
        Direction.SOUTH,
        Direction.NORTH
    );

    public Queen(final Player player) {
        super(player, PieceSymbol.QUEEN);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }

    @Override
    public double score(final boolean isSeveralPawn) {
        return PieceScore.QUEEN.score(isSeveralPawn);
    }
}
