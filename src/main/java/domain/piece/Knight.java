package domain.piece;

import domain.position.Position;

public class Knight extends Piece {

    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return distanceOneFileTwoRank(source, target) || distanceTwoFileOneRank(source, target);
    }

    private boolean distanceOneFileTwoRank(Position source, Position target) {
        return source.isLegalFileStep(target, ONE_STEP)
                && source.isLegalRankStep(target, TWO_STEP);
    }

    private boolean distanceTwoFileOneRank(Position source, Position target) {
        return source.isLegalFileStep(target, TWO_STEP)
                && source.isLegalRankStep(target, ONE_STEP);
    }
}
