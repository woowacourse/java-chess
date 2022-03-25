package chess.piece;

import chess.position.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isPossibleMovement(Position from, Position to) {
        return from.isDiagonalWay(to);
    }
}
