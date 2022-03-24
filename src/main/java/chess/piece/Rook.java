package chess.piece;

import chess.position.Position;

public class Rook extends Piece{

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected boolean isMovablePosition(Position to) {
        return isVerticalWay(to) || isHorizontalWay(to);
    }

    private boolean isVerticalWay(Position to) {
        return getPosition().isVerticalWay(to);
    }

    private boolean isHorizontalWay(Position to) {
        return getPosition().isHorizontalWay(to);
    }
}
