package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece {

    private static final int STRAIGHT_GAP = 0;

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    protected abstract boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor);

    public abstract boolean isEmpty();

    public abstract Piece move();

    public boolean canMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        if (getColor().isSame(targetColor)) {
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

    public Color getColor() {
        return color;
    }

    public boolean isSameColor(Color tagetColor) {
        return this.color == tagetColor;
    }
}
