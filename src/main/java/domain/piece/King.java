package domain.piece;

import domain.position.Position;

public class King extends AbstractPiece {
    private static final int DISTANCE_LIMIT_COUNT = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    public void validateMovement(Position source, Position target, Piece other) {
        int rankGap = source.calculateRankGap(target);
        int fileGap = source.calculateFileGap(target);
        if (Math.max(rankGap, fileGap) != DISTANCE_LIMIT_COUNT) {
            throw new IllegalArgumentException(String.format("%s은 한 번에 %d칸만 이동할 수 있습니다.",
                    this.getClass().getSimpleName(), DISTANCE_LIMIT_COUNT));
        }
        if (this.getColor() == other.getColor()) {
            throw new IllegalArgumentException("같은 팀의 말을 잡을 수 없습니다.");
        }
    }

    @Override
    public Type getType() {
        return Type.KING;
    }
}
