package domain.piece.pawn;

import domain.piece.Color;
import domain.position.Position;
import domain.position.Rank;

public class BlackPawn extends PawnPiece {
    private static final Rank INITIAL_RANK = Rank.SEVEN;

    public BlackPawn() {
        super(Color.BLACK);
    }

    @Override
    protected void validateForwardMovement(final Position source, final Position target) {
        if (!source.isUpperRankThan(target)) {
            throw new IllegalArgumentException("폰은 앞으로 이동해야 합니다.");
        }
    }

    @Override
    protected boolean isAtSameRank(final Position source) {
        return source.isAtSameRank(INITIAL_RANK);
    }

    @Override
    public Color color() {
        return Color.BLACK;
    }
}
