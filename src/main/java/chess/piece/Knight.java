package chess.piece;

import chess.position.Position;

public class Knight extends Piece{

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Knight(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to) {
        int horizontalDistance = getPosition().getHorizontalDistance(to);
        int verticalDistance = getPosition().getVerticalDistance(to);
        return (horizontalDistance == 1 && verticalDistance == 2) ||
            (horizontalDistance == 2 && verticalDistance == 1);
    }
}
