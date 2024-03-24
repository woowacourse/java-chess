package domain.piece;

import domain.position.Position;

public class King extends Piece {

    private static final int ZERO_STEP = 0;
    private static final int ONE_STEP = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.isLegalRankStep(target, ZERO_STEP, ONE_STEP)
                && source.isLegalFileStep(target, ZERO_STEP, ONE_STEP);
    }

    @Override
    public String display() {
        if (isBlack()) {
            return "K";
        }
        return "k";
    }
}
