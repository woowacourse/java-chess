package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {

    private static final int MAX_DISTANCE = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    protected boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        int fileGap = Math.abs(sourcePosition.calculateFileGap(targetPosition));
        int rankGap = Math.abs(sourcePosition.calculateRankGap(targetPosition));
        return fileGap <= MAX_DISTANCE && rankGap <= MAX_DISTANCE;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new King(getColor());
    }
}
