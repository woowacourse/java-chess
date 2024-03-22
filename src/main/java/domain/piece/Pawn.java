package domain.piece;

import domain.position.Position;
import domain.position.Rank;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        if (isBlack()) {
            if (source.hasRank(Rank.SEVEN)) {
                return source.isDown(target) && source.isLegalRankStep(target, 1, 2);
            }
            return source.isDown(target) && source.isLegalRankStep(target, 1);
        }
        if (source.hasRank(Rank.TWO)) {
            return source.isUp(target) && source.isLegalRankStep(target, 1, 2);
        }
        return source.isUp(target) && source.isLegalRankStep(target, 1);
    }

    @Override
    public boolean canAttack(Position source, Position target) {
        if (isBlack()) {
            if (source.hasRank(Rank.SEVEN)) {
                return source.isRightDown(target) && distanceOneRankOneFile(source, target)
                        || source.isLeftDown(target) && distanceOneRankOneFile(source, target);
            }
            return source.isRightDown(target) && distanceOneRankOneFile(source, target)
                    || source.isLeftDown(target) && distanceOneRankOneFile(source, target);
        }
        if (source.hasRank(Rank.TWO)) {
            return source.isRightUp(target) && distanceOneRankOneFile(source, target)
                    || source.isLeftUp(target) && distanceOneRankOneFile(source, target);
        }

        return source.isRightUp(target) && distanceOneRankOneFile(source, target)
                || source.isLeftUp(target) && distanceOneRankOneFile(source, target);
    }

    private boolean distanceOneRankOneFile(Position source, Position target) {
        return source.isLegalRankStep(target, 1) && source.isLegalFileStep(target, 1);
    }

    @Override
    public String display() {
        return "P";
    }
}
