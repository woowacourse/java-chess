package chess.piece;

import chess.Color;
import chess.Position;

public abstract class Piece {

    protected final Color color;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
