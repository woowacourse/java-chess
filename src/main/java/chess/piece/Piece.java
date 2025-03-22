package chess.piece;

import chess.Position;

public abstract class Piece {

    Piece() {
    }

    public abstract boolean canMove(Position start, Position target);

}
