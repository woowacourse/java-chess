package chess.piece;

import chess.position.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isPossibleMovement(Position from, Position to) {
        return from.isDiagonalWay(to) || from.isVerticalWay(to)
            || from.isHorizontalWay(to);
    }
}
