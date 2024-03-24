package domain.piece;

import domain.position.Position;
import domain.position.Rank;

public class Pawn extends Piece {

    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        if (isBlack()) {
            if (source.hasRank(Rank.SEVEN)) {
                return source.isDown(target) && source.isLegalRankStep(target, ONE_STEP, TWO_STEP);
            }
            return source.isDown(target) && source.isLegalRankStep(target, ONE_STEP);
        }
        if (source.hasRank(Rank.TWO)) {
            return source.isUp(target) && source.isLegalRankStep(target, ONE_STEP, TWO_STEP);
        }
        return source.isUp(target) && source.isLegalRankStep(target, ONE_STEP);
    }

    @Override
    public boolean canAttack(Position source, Position target) {
        if (isBlack()) {
            return source.isRightDown(target) && distanceOneRankOneFile(source, target)
                    || source.isLeftDown(target) && distanceOneRankOneFile(source, target);
        }
        return source.isRightUp(target) && distanceOneRankOneFile(source, target)
                || source.isLeftUp(target) && distanceOneRankOneFile(source, target);
    }

    private boolean distanceOneRankOneFile(Position source, Position target) {
        return source.isLegalRankStep(target, ONE_STEP) && source.isLegalFileStep(target, ONE_STEP);
    }

    @Override
    public String display() {
        if (isBlack()) {
            return "P";
        }
        return "p";
    }
}
