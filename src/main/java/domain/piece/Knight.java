package domain.piece;

import domain.position.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return isUpUpRight(source, target) || isUpUpLeft(source, target)
                || isDownDownRight(source, target) || isDownDownLeft(source, target)
                || isUpRightRight(source, target) || isUpLeftLeft(source, target)
                || isDownRightRight(source, target) || isDownLeftLeft(source, target);
    }

    private boolean isUpUpLeft(Position source, Position target) {
        return source.isLeftUp(target)
                && source.isLegalRankStep(target, 2)
                && source.isLegalFileStep(target, 1);
    }

    private boolean isUpUpRight(Position source, Position target) {
        return source.isRightUp(target)
                && source.isLegalRankStep(target, 2)
                && source.isLegalFileStep(target, 1);
    }

    private boolean isDownDownLeft(Position source, Position target) {
        return source.isLeftDown(target)
                && source.isLegalRankStep(target, 2)
                && source.isLegalFileStep(target, 1);
    }

    private boolean isDownDownRight(Position source, Position target) {
        return source.isRightDown(target)
                && source.isLegalRankStep(target, 2)
                && source.isLegalFileStep(target, 1);
    }

    private boolean isUpLeftLeft(Position source, Position target) {
        return source.isRightDown(target)
                && source.isLegalRankStep(target, 1)
                && source.isLegalFileStep(target, 2);
    }

    private boolean isUpRightRight(Position source, Position target) {
        return source.isRightDown(target)
                && source.isLegalRankStep(target, 1)
                && source.isLegalFileStep(target, 2);
    }

    private boolean isDownLeftLeft(Position source, Position target) {
        return source.isRightDown(target)
                && source.isLegalRankStep(target, 1)
                && source.isLegalFileStep(target, 2);
    }

    private boolean isDownRightRight(Position source, Position target) {
        return source.isRightDown(target)
                && source.isLegalRankStep(target, 1)
                && source.isLegalFileStep(target, 2);
    }

    @Override
    public String display() {
        return "N";
    }
}
