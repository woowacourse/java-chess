package chess.piece;

import chess.Position;

public abstract class Piece {

    protected Position position;

    public Piece(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
