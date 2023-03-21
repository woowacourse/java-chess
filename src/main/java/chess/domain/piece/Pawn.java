package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {

    private static final int MOVABLE_DISTANCE = 1;
    private static final int INITIAL_MOVABLE_DISTANCE = 2;
    private static final int VALID_STRAIGHT_GAP = 0;

    private int moveCount;

    public Pawn(Color color) {
        super(color);
    }

    public Pawn(Color color, int moveCount) {
        super(color);
        this.moveCount = moveCount;
    }

    @Override
    protected boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        return isStraightPawnMove(sourcePosition, targetPosition, targetColor) ||
                isDiagonalPawnMove(sourcePosition, targetPosition, targetColor);
    }

    private boolean isStraightPawnMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        if (sourcePosition.calculateFileGap(targetPosition) != VALID_STRAIGHT_GAP ||
                getColor().isOpposite(targetColor)) {
            return false;
        }
        int rankGap = sourcePosition.calculateRankGap(targetPosition);
        return canMoveStraightOne(rankGap) || canMoveStraightTwo(rankGap);
    }

    private boolean canMoveStraightOne(int rankGap) {
        return rankGap == MOVABLE_DISTANCE * getColor().getDirection();
    }

    private boolean canMoveStraightTwo(int rankGap) {
        return rankGap == INITIAL_MOVABLE_DISTANCE * getColor().getDirection() && isFirstMove();
    }

    private boolean isFirstMove() {
        return this.moveCount == 0;
    }

    private boolean isDiagonalPawnMove(Position sourcePosition, Position targetPosition, Color color) {
        int rankGap = sourcePosition.calculateRankGap(targetPosition);
        int fileGap = Math.abs(sourcePosition.calculateFileGap(targetPosition));
        boolean isOpponent = getColor().isOpposite(color);
        return rankGap == MOVABLE_DISTANCE * getColor().getDirection() && fileGap == MOVABLE_DISTANCE && isOpponent;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new Pawn(this.getColor(), moveCount + 1);
    }
}
