package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {

    private static final int STRAIGHT_GAP = 0;

    private final Color color;
    private final RoleType roleType;

    protected Piece(Color color, RoleType roleType) {
        this.color = color;
        this.roleType = roleType;
    }

    protected abstract boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor);

    public boolean canMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        if (isSameColor(targetColor)) {
            return false;
        }
        return validMove(sourcePosition, targetPosition, targetColor);
    }

    protected boolean isDiagonal(Position sourcePosition, Position targetPosition) {
        int columnGapAbs = Math.abs(sourcePosition.calculateFileGap(targetPosition));
        int rowGapAbs = Math.abs(sourcePosition.calculateRankGap(targetPosition));
        return columnGapAbs == rowGapAbs;
    }

    protected boolean isStraight(Position sourcePosition, Position targetPosition) {
        return sourcePosition.calculateFileGap(targetPosition) == STRAIGHT_GAP
                || sourcePosition.calculateRankGap(targetPosition) == STRAIGHT_GAP;
    }

    public boolean isSameColor(Color tagetColor) {
        return this.color == tagetColor;
    }

    public boolean isSameRoleType(RoleType roleType) {
        return this.roleType == roleType;
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return roleType.getScore();
    }
}
