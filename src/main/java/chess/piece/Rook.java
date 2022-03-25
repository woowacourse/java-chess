package chess.piece;

import chess.position.Position;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean isPossibleMovement(Position from, Position to) {
        return from.isVerticalWay(to) || from.isHorizontalWay(to);
    }
}
