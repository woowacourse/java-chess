package domain.piece;

import domain.position.Position;

public class Knight extends AbstractColorValidatablePiece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public void validatePieceMovement(final Position resource, final Position target) {
        int rankGap = resource.calculateRankGap(target);
        int fileGap = resource.calculateFileGap(target);
        if (rankGap * fileGap == 2) {
            return;
        }
        throw new IllegalArgumentException(String.format("%s은 L자 방향으로만 이동할 수 있습니다.",
                this.getClass().getSimpleName()));
    }

    @Override
    public Type getType() {
        return Type.KNIGHT;
    }
}
