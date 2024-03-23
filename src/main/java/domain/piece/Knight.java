package domain.piece;

import domain.position.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return isRightUpUp(source, target) || isLeftUpUp(source, target)
                || isRightDownDown(source, target) || isLeftDownDown(source, target)
                || isRightRightUp(source, target) || isLeftLeftUp(source, target)
                || isRightRightDown(source, target) || isLeftLeftDown(source, target);
    }

    private boolean isRightUpUp(Position source, Position target) {
        return source.isRightUp(target) && distanceOneFileTwoRank(source, target);
    }

    private boolean isLeftUpUp(Position source, Position target) {
        return source.isLeftUp(target) && distanceOneFileTwoRank(source, target);
    }

    private boolean isRightDownDown(Position source, Position target) {
        return source.isRightDown(target) && distanceOneFileTwoRank(source, target);
    }

    private boolean isLeftDownDown(Position source, Position target) {
        return source.isLeftDown(target) && distanceOneFileTwoRank(source, target);
    }

    private boolean isRightRightUp(Position source, Position target) {
        return source.isRightUp(target) && distanceTwoFileOneRank(source, target);
    }

    private boolean isLeftLeftUp(Position source, Position target) {
        return source.isLeftUp(target) && distanceTwoFileOneRank(source, target);
    }

    private boolean isRightRightDown(Position source, Position target) {
        return source.isRightDown(target) && distanceTwoFileOneRank(source, target);
    }

    private boolean isLeftLeftDown(Position source, Position target) {
        return source.isLeftDown(target) && distanceTwoFileOneRank(source, target);
    }

    private boolean distanceOneFileTwoRank(Position source, Position target) {
        return source.isLegalFileStep(target, 1) && source.isLegalRankStep(target, 2);
    }

    private boolean distanceTwoFileOneRank(Position source, Position target) {
        return source.isLegalFileStep(target, 2) && source.isLegalRankStep(target, 1);
    }

    @Override
    public String display() {
        if (isBlack()) {
            return "N";
        }
        return "n";
    }
}
