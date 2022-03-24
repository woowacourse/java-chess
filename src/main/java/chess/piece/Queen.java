package chess.piece;

import chess.position.Position;

public class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isMovablePosition(Position to) {
        return isDiagonalWay(to) || isVerticalWay(to) || isHorizontalWay(to);
    }

    private boolean isDiagonalWay(Position to) {
        return getPosition().isDiagonalWay(to);
    }

    private boolean isVerticalWay(Position to) {
        return getPosition().isVerticalWay(to);
    }

    private boolean isHorizontalWay(Position to) {
        return getPosition().isHorizontalWay(to);
    }
}
