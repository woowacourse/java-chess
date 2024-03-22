package domain.piece;

import domain.position.Position;

public class Bishop extends AbstractColorValidatablePiece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected void validatePieceMovement(final Position resource, final Position target) {
        int rankGap = resource.calculateRankGap(target);
        int fileGap = resource.calculateFileGap(target);
        if (rankGap == 0 && fileGap == 0) {
            throw new IllegalArgumentException(String.format("%s은 대각선 방향으로만 이동할 수 있습니다.",
                    this.getClass().getSimpleName()));
        }
        if (rankGap == fileGap) {
            return;
        }
        throw new IllegalArgumentException(String.format("%s은 대각선 방향으로만 이동할 수 있습니다.",
                this.getClass().getSimpleName()));
    }

    @Override
    public Type getType() {
        return Type.BISHOP;
    }
}
