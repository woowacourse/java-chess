package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {

        return (isStraight(sourcePosition, targetPosition) || isDiagonal(sourcePosition, targetPosition))
                && isNotMyPosition(sourcePosition, targetPosition) && isNotSameColor(color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new Queen(getColor());
    }
}
