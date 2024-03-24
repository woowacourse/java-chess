package domain.strategy;

import domain.position.Position;
import domain.position.Rank;
import domain.position.UnitVector;
import java.util.Set;

public final class BlackPawnMoveStrategy extends PawnMoveStrategy {
    private static final Rank BLACK_PAWN_INITIAL_RANK = Rank.SEVEN;
    private static final UnitVector BLACK_PAWN_STRAIGHT_VECTOR = UnitVector.DOWN;
    private static final Set<UnitVector> BLACK_PAWN_CROSS_VECTORS = Set.of(UnitVector.DOWN_LEFT, UnitVector.DOWN_RIGHT);

    @Override
    protected Set<UnitVector> getCrossValidVectors() {
        return BLACK_PAWN_CROSS_VECTORS;
    }

    @Override
    protected UnitVector getStraightValidVector() {
        return BLACK_PAWN_STRAIGHT_VECTOR;
    }

    @Override
    protected boolean isInitialPosition(Position position) {
        return BLACK_PAWN_INITIAL_RANK.equals(position.rank());
    }
}
