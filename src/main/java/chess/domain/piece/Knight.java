package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    protected boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        int rankGap = Math.abs(sourcePosition.calculateRankGap(targetPosition));
        int fileGap = Math.abs(sourcePosition.calculateFileGap(targetPosition));
        return isMovable(rankGap, fileGap) || isMovable(fileGap, rankGap);
    }

    private boolean isMovable(int firstGap, int secondGap) {
        return firstGap == 1 && secondGap == 2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new Knight(getColor());
    }
}
