package domain.strategy;

import domain.position.Position;
import domain.position.Rank;
import domain.position.UnitVector;
import java.util.Set;

public final class WhitePawnMoveStrategy extends PawnMoveStrategy {
    private static final Rank WHITE_PAWN_INITIAL_RANK = Rank.TWO;
    private static final UnitVector WHITE_PAWN_STRAIGHT_VECTOR = UnitVector.UP;
    private static final Set<UnitVector> WHITE_PAWN_CROSS_VECTORS = Set.of(UnitVector.UP_LEFT, UnitVector.UP_RIGHT);

    @Override
    protected Set<UnitVector> getCrossValidVectors() {
        return WHITE_PAWN_CROSS_VECTORS;
    }

    @Override
    protected UnitVector getStraightValidVector() {
        return WHITE_PAWN_STRAIGHT_VECTOR;
    }

    @Override
    protected boolean isInitialPosition(Position position) {
        return WHITE_PAWN_INITIAL_RANK.equals(position.rank());
    }
}
