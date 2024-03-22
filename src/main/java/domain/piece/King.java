package domain.piece;

import domain.position.Position;

public class King extends AbstractColorValidatablePiece {
    private static final int DISTANCE_LIMIT_COUNT = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    public void validatePieceMovement(final Position resource, final Position target) {
        int rankGap = resource.calculateRankGap(target);
        int fileGap = resource.calculateFileGap(target);
        if (Math.max(rankGap, fileGap) != DISTANCE_LIMIT_COUNT) {
            throw new IllegalArgumentException(String.format("%s은 한 번에 %d칸만 이동할 수 있습니다.",
                    this.getClass().getSimpleName(), DISTANCE_LIMIT_COUNT));
        }
    }

    @Override
    public Type getType() {
        return Type.KING;
    }
}
