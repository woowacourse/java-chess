package chess.piece;

import chess.*;

public class Pawn extends Piece {

    private boolean isFirstMove;

    public Pawn(Color color, Position position) {
        super(color, position);
        this.isFirstMove = true;
    }

    @Override
    public void move(Position to) {
        if (isInvalidMovement(to)) {
            throw new IllegalArgumentException();
        }

        this.isFirstMove = false;
        this.position = to;
    }

    private boolean isInvalidMovement(Position to) {
        return isInvalidDirection(to) || isInvalidDistance(to) ||
            isMoveOverOneSpaceAfterFirstMove(to);
    }

    private boolean isInvalidDirection(Position to) {
        Direction direction = position.getDirectionTo(to);
        return !position.isVerticalWay(to) || color.isBackward(direction);
    }

    private boolean isInvalidDistance(Position to) {
        return position.getHorizontalDistance(to) > 2;
    }

    private boolean isMoveOverOneSpaceAfterFirstMove(Position to) {
        return !isFirstMove && position.getHorizontalDistance(to) >= 2;
    }

}
